package com.stylefeng.guns.rest.modular.examine.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.classMGR.service.IClassService;
import com.stylefeng.guns.modular.examineMGR.service.IClassExamStrategyService;
import com.stylefeng.guns.modular.system.model.Class;
import com.stylefeng.guns.modular.system.model.Teacher;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import com.stylefeng.guns.rest.modular.examine.requester.BeginExamineRequester;
import com.stylefeng.guns.rest.modular.examine.requester.ExamPaperSubmitRequester;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 测试
 *
 * Created by 罗华.
 */
@RestController
@RequestMapping("/examine")
@Api(tags = "考试接口")
public class ExamController {

    @Autowired
    private IClassService classService;

    @Autowired
    private IClassExamStrategyService classExamStrategyService;

    @ApiOperation(value="出题", httpMethod = "POST", response = Teacher.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subject", value = "学科", required = false, dataType = "String"),
    })
    @RequestMapping("/begin")
    public Responser beginExamine(
            @ApiParam(required = true, value = "开始测试请求")
            @RequestBody
            @Valid
            BeginExamineRequester requester
    ){

        String classCode = requester.getClassCode();
        Class classInfo = classService.selectOne(new EntityWrapper<Class>().eq("code", classCode).eq("status", GenericState.Valid.code));

        if (null == classInfo)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        classExamStrategyService.generateExamine(requester.getStudent(), classInfo, requester.getGrade(), requester.getAbility());

        return SimpleResponser.success();
    }

    @ApiOperation(value="试卷列表", httpMethod = "POST", response = Teacher.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subject", value = "学科", required = false, dataType = "String"),
    })
    @RequestMapping("/list")
    public Responser listPaper(String subject, Integer method){
        return null;
    }

    @ApiOperation(value="试卷详情")
    @RequestMapping(value = "/paper/detail/{code}", method = {RequestMethod.POST, RequestMethod.GET})
    public Responser paperDetail(@PathVariable("code") String code){
        return null;
    }

    @ApiOperation(value="提交试卷", httpMethod = "POST")
    @RequestMapping("/paper/submit")
    public Responser submitPaper(@RequestBody ExamPaperSubmitRequester requester){
        return null;
    }
}
