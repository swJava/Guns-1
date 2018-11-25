package com.stylefeng.guns.rest.modular.education.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.classRoomMGR.service.IClassroomService;
import com.stylefeng.guns.modular.classRoomMGR.service.impl.ClassroomServiceImpl;
import com.stylefeng.guns.modular.system.model.Classroom;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.modular.education.responser.ClassDetailResponse;
import com.stylefeng.guns.rest.modular.education.responser.ClassListResponse;
import com.stylefeng.guns.rest.modular.education.responser.ClassroomDetailResponse;
import com.stylefeng.guns.rest.modular.education.responser.CourseDetailResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 罗华.
 */
@RestController
@RequestMapping("/education")
@Api(value = "EducationController", tags = "教务接口")
public class EducationController {

    @Autowired
    private IClassroomService classroomService;

    @RequestMapping(value = "/class/list", method = RequestMethod.POST)
    @ApiOperation(value="班级列表", httpMethod = "POST", response = ClassListResponse.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "subject", value = "学科", required = false, dataType = "String"),
        @ApiImplicitParam(name = "classCycle", value = "学期", required = false, dataType = "String"),
        @ApiImplicitParam(name = "classLevel", value = "班次", required = false, dataType = "String"),
        @ApiImplicitParam(name = "method", value = "授课方式", required = false, dataType = "Integer"),
        @ApiImplicitParam(name = "weekday", value = "上课时间，周几", required = false, dataType = "String"),
        @ApiImplicitParam(name = "teacherCode", value = "授课教师", required = false, dataType = "String"),
        @ApiImplicitParam(name = "assisterCode", value = "辅导员", required = false, dataType = "String"),
        @ApiImplicitParam(name = "classroomCode", value = "教室", required = false, dataType = "String")
    })
    public Responser 班级列表(String subject, Integer classCycle, Integer classLevel, Integer method, Integer weekday, String teacherCode, String assisterCode, String classroomCode){
        return null;
    }

    @ApiOperation(value="班级详情", httpMethod = "POST", response = ClassDetailResponse.class)
    @ApiImplicitParam(name = "code", value = "班级编码", required = true, dataType = "String")
    @RequestMapping("/class/detail/{code}")
    public Responser 班级详情(@PathVariable("code") String code) {
        return null;
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
        Wrapper<Classroom> queryWrapper = new EntityWrapper<Classroom>();

        Classroom classroom = classroomService.selectOne(queryWrapper);

        if (null == classroom)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        return ClassroomDetailResponse.me(classroom);
    }


}
