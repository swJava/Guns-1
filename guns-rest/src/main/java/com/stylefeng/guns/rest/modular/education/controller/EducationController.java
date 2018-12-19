package com.stylefeng.guns.rest.modular.education.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.classMGR.service.IClassService;
import com.stylefeng.guns.modular.classMGR.service.ICourseOutlineService;
import com.stylefeng.guns.modular.classMGR.service.ICourseService;
import com.stylefeng.guns.modular.classRoomMGR.service.IClassroomService;
import com.stylefeng.guns.modular.education.service.IScheduleStudentService;
import com.stylefeng.guns.modular.memberMGR.service.IMemberService;
import com.stylefeng.guns.modular.studentMGR.service.IStudentService;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.rest.core.ApiController;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.modular.education.requester.*;
import com.stylefeng.guns.rest.modular.education.responser.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "/class/list", method = RequestMethod.POST)
    @ApiOperation(value="班级列表", httpMethod = "POST", response = ClassListResponse.class)
    public Responser listClass(ClassQueryRequester requester, HttpServletRequest request){

        String userName = (String) request.getAttribute("USER_NAME");

        List<com.stylefeng.guns.modular.system.model.Class> classList = classService.queryForList(userName, requester.toMap());

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
    @RequestMapping("/outline/list")
    public Responser outlineList(
            @ApiParam(required = true, value = "课时查询")
            @RequestBody
            @Valid
            OutlineListQueryRequester requester){

        Member member = currMember();

        List<Student> studentList = new ArrayList<Student>();
        if (requester.notDirectStudent()){
            studentList = studentService.listStudents(member.getUserName());
        }

        List<ScheduleStudent> planList = new ArrayList<ScheduleStudent>();
        for(Student currStudent : studentList) {
            planList.addAll(studentService.listCoursePlan(requester.getClassCode(), currStudent.getCode()));
            if (planList.isEmpty()){
                continue;
            }

            break;
        }

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

        return OutlineListResponser.me(outlineResponseList);
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
    public Responser listClass4Adjust(AdjustQueryRequester requester){

        Member currMember = currMember();


        return null;
    }

    @RequestMapping(value = "/class/list4change", method = RequestMethod.POST)
    @ApiOperation(value="可转班班级列表", httpMethod = "POST", response = ClassListResponse.class)
    public Responser listClass4Change(ClassQueryRequester requester, HttpServletRequest request){

        String userName = (String) request.getAttribute("USER_NAME");

        List<com.stylefeng.guns.modular.system.model.Class> classList = classService.queryForList(userName, requester.toMap());

        return ClassListResponse.me(classList);
    }

    @RequestMapping("/adjust/course")
    @ApiOperation(value = "调课申请", httpMethod = "POST")
    @ResponseBody
    public Responser 调课申请(
            @ApiParam(required = true, value = "调课申请")
            @RequestBody
            AdjustApplyRequester requester) {
        return null;
    }

    @RequestMapping("/adjust/class")
    @ApiOperation(value = "转班申请", httpMethod = "POST")
    @ResponseBody
    public Responser 转班申请(
            @ApiParam(required = true, value = "转班申请")
            @RequestBody
            AdjustApplyRequester requester) {
        return null;
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
        String userName = (String) request.getAttribute("USER_NAME");

        List<Student> studentList = studentService.listStudents(userName);

        if (studentList.isEmpty()){
            log.warn("Member {} have not student");
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"学生"});
        }

        List<ScheduleStudent> planList = new ArrayList<ScheduleStudent>();
        for (Student student : studentList){
            planList.addAll(studentService.listCoursePlan(student.getCode(), requester.getDay(), new Integer[]{0, 1}));
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
