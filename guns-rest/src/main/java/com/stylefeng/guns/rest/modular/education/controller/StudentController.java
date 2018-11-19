package com.stylefeng.guns.rest.modular.education.controller;

import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.modular.education.requester.StudentAddRequester;
import com.stylefeng.guns.rest.modular.education.requester.StudentChangeRequester;
import com.stylefeng.guns.rest.modular.education.responser.StudentDetailResponse;
import com.stylefeng.guns.rest.modular.education.responser.StudentListResponse;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 罗华.
 */
@RestController
@RequestMapping("/student")
@Api(tags = "学员接口")
public class StudentController {

    @RequestMapping("/add")
    @ApiOperation(value="添加学员", httpMethod = "POST")
    public Responser 添加学员(
            @ApiParam(required = true, value = "学员信息")
            @RequestBody StudentAddRequester requester){
        return null;
    }

    @RequestMapping("/list/{userName}")
    @ApiOperation(value="学员列表", httpMethod = "POST", response = StudentListResponse.class)
    public Responser 会员所属学员列表(
            @PathVariable("userName") String userName){
        return null;
    }

    @RequestMapping("/get/{code}")
    @ApiOperation(value="学员详情", httpMethod = "POST", response = StudentDetailResponse.class)
    @ApiImplicitParam(name = "code", value = "学员编码", required = true, dataType = "String", example = "XY000001")
    public Responser 学员详情(
            @PathVariable("code") String code){
        return null;
    }


    @RequestMapping(value = "/info/change/{code}", method = RequestMethod.POST)
    @ApiOperation(value="学员信息修改", httpMethod = "POST")
    public Responser 学员信息修改(
            @PathVariable("code") String code,
            @RequestBody StudentChangeRequester requester
    ){
        return null;
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
