package com.stylefeng.guns.rest.modular.education.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.adjustMGR.service.IAdjustStudentService;
import com.stylefeng.guns.modular.classMGR.service.IClassService;
import com.stylefeng.guns.modular.classMGR.service.ICourseOutlineService;
import com.stylefeng.guns.modular.classMGR.service.ICourseService;
import com.stylefeng.guns.modular.classRoomMGR.service.IClassroomService;
import com.stylefeng.guns.modular.education.service.IScheduleClassService;
import com.stylefeng.guns.modular.education.service.IScheduleStudentService;
import com.stylefeng.guns.modular.memberMGR.service.IMemberService;
import com.stylefeng.guns.modular.studentMGR.service.IStudentService;
import com.stylefeng.guns.modular.system.model.Class;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.rest.core.ApiController;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import com.stylefeng.guns.rest.modular.education.requester.*;
import com.stylefeng.guns.rest.modular.education.responser.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by 罗华.
 */
@RestController
@RequestMapping("/education")
@Api(value = "EducationController", tags = "教务接口")
public class EducationController extends ApiController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IClassroomService classroomService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IClassService classService;

    @Autowired
    private IMemberService memberService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ICourseOutlineService courseOutlineService;

    @Autowired
    private IScheduleStudentService scheduleStudentService;

    @Autowired
    private IScheduleClassService scheduleClassService;

    @Autowired
    private IAdjustStudentService adjustStudentService;

    @Value("${application.education.adjust.maxTimes:4}")
    private int maxAdjustTimes = 4;

    @RequestMapping(value = "/class/list", method = RequestMethod.POST)
    @ApiOperation(value="班级列表", httpMethod = "POST", response = ClassListResponse.class)
    public Responser listClass(ClassQueryRequester requester, HttpServletRequest request){

        Member member = currMember();

        List<com.stylefeng.guns.modular.system.model.Class> classList = classService.queryForList(member.getUserName(), requester.toMap());

        return ClassListResponse.me(classList);
    }

    @ApiOperation(value="班级详情", httpMethod = "POST", response = ClassDetailResponse.class)
    @ApiImplicitParam(name = "code", value = "班级编码", required = true, dataType = "String")
    @RequestMapping("/class/detail/{code}")
    public Responser detailForClass(@PathVariable("code") String code) {
        Wrapper<com.stylefeng.guns.modular.system.model.Class> queryWrapper = new EntityWrapper<com.stylefeng.guns.modular.system.model.Class>();
        queryWrapper.eq("code", code);

        com.stylefeng.guns.modular.system.model.Class classInfo = classService.selectOne(queryWrapper);

        if (null == classInfo)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        return ClassDetailResponse.me(classInfo);
    }

    @ApiOperation(value="课时列表", httpMethod = "POST", response = OutlineListResponser.class)
    @ApiImplicitParam(name = "code", value = "班级编码", required = true, dataType = "String")
    @RequestMapping(value = "/outline/list", method = RequestMethod.POST)
    public Responser outlineList(
            @ApiParam(required = true, value = "课时查询")
            @RequestBody
            @Valid
            OutlineListQueryRequester requester){

        Member member = currMember();

        List<Student> studentList = new ArrayList<Student>();
        if (requester.notDirectStudent()){
            // 没有指定学员, 找到用户下所有学员列表
            studentList = studentService.listStudents(member.getUserName());
        }else{
            studentList.add(studentService.get(requester.getStudent()));
        }

        List<ScheduleStudent> planList = new ArrayList<ScheduleStudent>();
        Student student = null;
        for(Student currStudent : studentList) {
            planList.addAll(studentService.listCoursePlan(requester.getClassCode(), currStudent.getCode()));
            if (planList.isEmpty()){
                continue;
            }

            student = currStudent;
            break;
        }

        // 查找已调课次数
        int adjustTimes = adjustStudentService.countAdjust(requester.getClassCode(), student.getCode(), AdjustStudentTypeEnum.Adjust);


        List<OutlineResponse> outlineResponseList = new ArrayList<OutlineResponse>();
        for(ScheduleStudent plan : planList){
            String outlineCode = plan.getOutlineCode();

            CourseOutline outline = courseOutlineService.get(outlineCode);

            if (null == outline)
                continue;

            OutlineResponse response = OutlineResponse.me(outline);

            if (!plan.isValid()){
                // 可能是已调课，找到当前有效的课程计划
                ScheduleStudent adjustedSchedule = scheduleStudentService.getAdjustedSchedule(plan.getCode());
                if (null == adjustedSchedule) {
                    response.setCanAdjust(false);
                } else{
                    CourseOutline newoutline = courseOutlineService.get(adjustedSchedule.getOutlineCode());
                    response = OutlineResponse.me(newoutline);
                    response.setCanAdjust(true);
                }
            } else {
                if (plan.isOver()){
                    // 已上课
                    response.setCanAdjust(false);
                }else {
                    response.setCanAdjust(true);
                }
            }

            outlineResponseList.add(response);
        }

        return OutlineListResponser.me(maxAdjustTimes - adjustTimes, outlineResponseList);
    }

    @ApiOperation(value="教室详情", httpMethod = "POST", response = ClassroomDetailResponse.class)
    @ApiImplicitParam(name = "code", value = "教室编码", required = true, dataType = "String", example = "JS000001")
    @RequestMapping("/classroom/detail/{code}")
    @ResponseBody
    public Responser detailForClassroom(@PathVariable("code") String code){
        Wrapper<Classroom> queryWrapper = new EntityWrapper<Classroom>();

        Classroom classroom = classroomService.selectOne(queryWrapper);

        if (null == classroom)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        return ClassroomDetailResponse.me(classroom);
    }

    @RequestMapping(value = "/course/detail/{code}", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value="课程详情", httpMethod = "POST", response = CourseDetailResponse.class)
    @ApiImplicitParam(name = "code", value = "课程编码", required = true, dataType = "String", example = "KC000001")
    public Responser detailForCourse(@PathVariable("code") String code) {
        Wrapper<Course> queryWrapper = new EntityWrapper<Course>();
        queryWrapper.eq("code", code);

        Course course = courseService.selectOne(queryWrapper);

        if (null == course)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        return CourseDetailResponse.me(course);
    }

    @RequestMapping(value = "/class/list4adjust", method = RequestMethod.POST)
    @ApiOperation(value="可调课班级列表", httpMethod = "POST", response = ClassListResponse.class)
    public Responser listClass4Adjust(
            @RequestBody
            @Valid
            AdjustQueryRequester requester){

        Member currMember = currMember();
        // 当前报班信息
        Class currClassInfo = classService.get(requester.getClassCode());

        Wrapper<ScheduleClass> planQueryWrapper = new EntityWrapper<>();
        planQueryWrapper.eq("outline_code", requester.getOutlineCode());
        planQueryWrapper.eq("status", GenericState.Valid.code);

        List<ScheduleClass> classPlanList = scheduleClassService.selectList(planQueryWrapper);
        Set<Class> classResponserSet = new HashSet<>();
        for(ScheduleClass classPlan : classPlanList){
            Class classInfo = classService.get(classPlan.getClassCode());
            if (null == classInfo){
                continue;
            }
            if (!(classInfo.isValid())){
                continue;
            }
            if (currClassInfo.getCode().equals(classInfo.getCode())){
                // 过滤掉自己
                continue;
            }
            // TODO 是否根据当前报班情况限制？

            classResponserSet.add(classInfo);
        }

        return ClassListResponse.me(classResponserSet);
    }

    @RequestMapping(value = "/class/list4change", method = RequestMethod.POST)
    @ApiOperation(value="可转班班级列表", httpMethod = "POST", response = ClassListResponse.class)
    public Responser listClass4Change(
            @RequestBody
            @Valid
            AdjustQueryRequester requester){

        Member member = currMember();

        Class currClass = classService.get(requester.getClassCode());
        Course course = courseService.get(currClass.getCourseCode());

        Map<String, Object> changeClassQuery = new HashMap<String, Object>();
        changeClassQuery.put("grades", String.valueOf(currClass.getGrade()));
        changeClassQuery.put("abilities", String.valueOf(currClass.getAbility()));
        changeClassQuery.put("subjects", course.getSubject());

        List<com.stylefeng.guns.modular.system.model.Class> classList = classService.queryForList(member.getUserName(), changeClassQuery);

        Set<Class> classSet = new HashSet<>();
        for (com.stylefeng.guns.modular.system.model.Class classInfo : classList){
            if (null == classInfo){
                continue;
            }
            if (!(classInfo.isValid())){
                continue;
            }
            if (classInfo.getCode().equals(currClass.getCode())){
                // 过滤掉自己
                continue;
            }
            if (currClass.getPrice().equals(classInfo.getPrice())){
                classSet.add(classInfo);
            }
        }

        return ClassListResponse.me(classSet);
    }

    @RequestMapping(value = "/adjust/course", method = RequestMethod.POST)
    @ApiOperation(value = "调课申请", httpMethod = "POST", response = SimpleResponser.class)
    @ResponseBody
    public Responser adjustCourse(
            @ApiParam(required = true, value = "调课申请")
            @RequestBody
            @Valid
            AdjustApplyRequester requester) {
        Member member = currMember();

        Student student = studentService.get(requester.getStudentCode());

        Class sourceClass = classService.get(requester.getSourceClass());
        Class targetClass = classService.get(requester.getTargetClass());

        Map<String, Object> fromData = new HashMap<>();
        fromData.put("sourceClass", sourceClass);
        fromData.put("outlineCode", requester.getOutlineCode());

        Map<String, Object> destData = new HashMap<>();
        destData.put("targetClass", targetClass);

        adjustStudentService.adjustCourse(member, student, fromData, destData);

        return SimpleResponser.success();
    }

    @RequestMapping(value = "/adjust/class", method = RequestMethod.POST)
    @ApiOperation(value = "转班申请", httpMethod = "POST", response = SimpleResponser.class)
    @ResponseBody
    public Responser changeClass(
            @ApiParam(required = true, value = "转班申请")
            @RequestBody
            @Valid
            ChangeApplyRequester requester) {

        Member member = currMember();

        Student student = studentService.get(requester.getStudentCode());

        Class sourceClass = classService.get(requester.getSourceClass());
        Class targetClass = classService.get(requester.getTargetClass());

        Map<String, Object> fromData = new HashMap<>();
        fromData.put("sourceClass", sourceClass);

        Map<String, Object> destData = new HashMap<>();
        destData.put("targetClass", targetClass);

        adjustStudentService.adjustClass(member, student, fromData, destData);

        return SimpleResponser.success();
    }

    @ApiOperation(value="课程表", httpMethod = "POST")
    @RequestMapping("/course/plan/list")
    public Responser 课程表(String studentCode, String classCode){
        return null;
    }

    @ApiOperation(value="单天课程表", httpMethod = "POST", response = PlanOfDayResponserWrapper.class)
    @RequestMapping(value = "/course/plan/day", method = RequestMethod.POST)
    public Responser queryPlanOfDay(
            @ApiParam(required = true, value = "单天课程表查询")
            @RequestBody
            @Valid
            QueryPlanOfDayRequester requester, HttpServletRequest request
    ){
        Member member = currMember();

        List<Student> studentList = studentService.listStudents(member.getUserName());

        if (studentList.isEmpty()){
            log.warn("Member {} have not student");
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"学生"});
        }

        List<ScheduleStudent> planList = new ArrayList<ScheduleStudent>();
        for (Student student : studentList){
            planList.addAll(studentService.listCoursePlan(student.getCode(), requester.getDay(), new Integer[]{1}));
        }

        List<PlanOfDayResponser> responserList = new ArrayList<PlanOfDayResponser>();
        for(ScheduleStudent plan : planList){
            com.stylefeng.guns.modular.system.model.Class classInfo = classService.get(plan.getClassCode());
            if (null == classInfo) {
                log.warn("Class info is null");
                continue;
            }

            CourseOutline outline = courseOutlineService.get(plan.getOutlineCode());
            if (null == outline) {
                log.warn("Class info is null");
                continue;
            }

            responserList.add(PlanOfDayResponser.me(ClassResponser.me(classInfo), outline));
        }

        return PlanOfDayResponserWrapper.me(responserList);
    }

}
