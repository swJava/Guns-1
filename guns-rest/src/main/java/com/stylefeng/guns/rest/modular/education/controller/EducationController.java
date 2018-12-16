package com.stylefeng.guns.rest.modular.education.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.classMGR.service.IClassService;
import com.stylefeng.guns.modular.classMGR.service.ICourseService;
import com.stylefeng.guns.modular.classRoomMGR.service.IClassroomService;
import com.stylefeng.guns.modular.system.model.Classroom;
import com.stylefeng.guns.modular.system.model.Course;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.modular.education.requester.AdjustApplyRequester;
import com.stylefeng.guns.rest.modular.education.requester.ClassQueryRequester;
import com.stylefeng.guns.rest.modular.education.responser.ClassDetailResponse;
import com.stylefeng.guns.rest.modular.education.responser.ClassListResponse;
import com.stylefeng.guns.rest.modular.education.responser.ClassroomDetailResponse;
import com.stylefeng.guns.rest.modular.education.responser.CourseDetailResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 罗华.
 */
@RestController
@RequestMapping("/education")
@Api(value = "EducationController", tags = "教务接口")
public class EducationController {

    @Autowired
    private IClassroomService classroomService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IClassService classService;

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
    public Responser listClass4Adjust(ClassQueryRequester requester, HttpServletRequest request){

        String userName = (String) request.getAttribute("USER_NAME");

        List<com.stylefeng.guns.modular.system.model.Class> classList = classService.queryForList(userName, requester.toMap());

        return ClassListResponse.me(classList);
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

}
