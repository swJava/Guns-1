package com.stylefeng.guns.rest.modular.examine.controller;

import com.stylefeng.guns.modular.system.model.Teacher;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.modular.examine.requester.ExamPaperSubmitRequester;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * Created by 罗华.
 */
@RestController
@RequestMapping("/examine")
@Api(tags = "考试接口")
public class ExamController {

    @ApiOperation(value="试卷列表", httpMethod = "POST", response = Teacher.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subject", value = "学科", required = false, dataType = "String"),
            @ApiImplicitParam(name = "method", value = "授课方式", required = false, dataType = "Integer")
    })
    @RequestMapping("/list")
    public Responser 试卷列表(String subject, Integer method){
        return null;
    }

    @ApiOperation(value="试卷详情", httpMethod = "POST")
    @ApiImplicitParam(name = "code", value = "试卷编码", required = true, dataType = "String")
    @RequestMapping("/paper/get")
    public Responser 试卷(String code){
        return null;
    }

    @ApiOperation(value="提交试卷", httpMethod = "POST")
    @RequestMapping("/paper/submit")
    public Responser 提交试卷(@RequestBody ExamPaperSubmitRequester requester){
        return null;
    }
}
