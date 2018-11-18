package com.stylefeng.guns.rest.modular.education.controller;

import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.modular.education.responser.TeacherDetailResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 教师
 *
 * Created by 罗华.
 */
@Api(tags = "教师接口")
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @ApiOperation(value="教师详情", httpMethod = "POST", response = TeacherDetailResponse.class)
    @ApiImplicitParam(name = "code", value = "教师编码", required = true, dataType = "String", example = "LS000001")
    @RequestMapping("/detail/{code}")
    public Responser 教师详情(@PathVariable("code") String code){
        return null;
    }
}
