package com.stylefeng.guns.rest.modular.education.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.modular.studentMGR.service.IStudentService;
import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import com.stylefeng.guns.rest.modular.education.requester.StudentAddRequester;
import com.stylefeng.guns.rest.modular.education.requester.StudentChangeRequester;
import com.stylefeng.guns.rest.modular.education.responser.StudentDetailResponse;
import com.stylefeng.guns.rest.modular.education.responser.StudentListResponse;
import com.stylefeng.guns.rest.modular.member.responser.RegistResponse;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by 罗华.
 */
@RestController
@RequestMapping("/student")
@Api(tags = "学员接口")
@Validated
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @RequestMapping("/add")
    @ApiOperation(value="添加学员", httpMethod = "POST",response = RegistResponse.class )
    @ResponseBody
    public Responser addStudent(
            @ApiParam(required = true, value = "学员信息")
            @RequestBody
            @Valid
            StudentAddRequester requester){

        String userName = requester.getUserName();

        Student student = studentService.addStudent(userName, requester.toMap());

        return SimpleResponser.success();
    }

    @RequestMapping("/list/{userName}")
    @ApiOperation(value="学员列表", httpMethod = "POST", response = StudentListResponse.class)
    @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", example = "MM00021")
    @ResponseBody
    public Responser listStudent(
            @PathVariable(value = "userName", required = true)
            @NotBlank(message = "用户名不能为空")
            String userName){

        Wrapper<Student> queryWrapper = new EntityWrapper<Student>();

        queryWrapper.eq("user_name", userName);

        List<Student> studentList = studentService.selectList(queryWrapper);

        return StudentListResponse.me(studentList);
    }

    @RequestMapping("/detail/{code}")
    @ApiOperation(value="学员详情", httpMethod = "POST", response = StudentDetailResponse.class)
    @ApiImplicitParam(name = "code", value = "学员编码", required = true, dataType = "String", example = "XY000001")
    public Responser detailStudent(
            @PathVariable(value = "code", required = true)
            @NotBlank(message = "学员号不能为空")
            String code){

        Wrapper<Student> queryWrapper = new EntityWrapper<Student>();

        queryWrapper.eq("code", code);

        Student student = studentService.selectOne(queryWrapper);

        return StudentDetailResponse.me(student);
    }


    @RequestMapping(value = "/info/change", method = RequestMethod.POST)
    @ApiOperation(value="学员信息修改", httpMethod = "POST", response = SimpleResponser.class)
    public Responser changeInfo(
            @RequestBody
            @Valid
            StudentChangeRequester requester
    ){
        Student newStudent = new Student();
        org.springframework.beans.BeanUtils.copyProperties(requester, newStudent);
        newStudent.setGender(requester.getGendar());

        Long avatorId = requester.getAvatorId();
        if ( null != avatorId && avatorId > 0L){
            newStudent.setAvatar(studentService.getAvatarViewUrl(avatorId));
        }

        studentService.updateStudent(requester.getCode(), newStudent);
        return SimpleResponser.success();
    }

    @ApiOperation(value="课程表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentCode", value = "学员编码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "classCode", value = "班级编码", required = true, dataType = "String")
    })
    @RequestMapping("/course/list")
    public Responser 课程表(String studentCode, String classCode){
        return null;
    }
}
