package com.stylefeng.guns.rest.modular.member.controller;

import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.memberMGR.service.IMemberService;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.service.ICaptchaService;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.modular.education.requester.AdjustApplyRequester;
import com.stylefeng.guns.rest.modular.member.requester.LoginRequester;
import com.stylefeng.guns.rest.modular.member.requester.MemberCahngeRequester;
import com.stylefeng.guns.rest.modular.member.requester.PasswordChangeRequester;
import com.stylefeng.guns.rest.modular.member.requester.RegistRequester;
import com.stylefeng.guns.rest.modular.member.responser.MemberDetailResponse;
import com.stylefeng.guns.rest.modular.member.responser.RegistResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * 认证
 *
 * Created by 罗华.
 */
@Api(tags = "会员接口")
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @Autowired
    private ICaptchaService captchaService;

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ApiOperation(value="用户注册", httpMethod = "POST", response = RegistResponse.class)
    public Responser regist(
            @ApiParam(required = true, value = "注册提交信息")
            @RequestBody
            @Valid
            RegistRequester requester){

        String captcha = requester.getCaptcha();
        String userName = requester.getUserName();

        if (!captchaService.checkCaptcha(userName, captcha))
            throw new ServiceException(MessageConstant.MessageCode.SYS_CAPTCHA_NOT_MATCH);

        Member member = memberService.createMember(requester.getUserName(), requester.getPassword(), requester.toMap());
        return RegistResponse.me(member);
    }

    @RequestMapping("/login")
    @ApiOperation(value="用户登录", httpMethod = "POST")
    public Responser 登录(
            @ApiParam(required = true, value = "登录信息")
            @RequestBody
            LoginRequester requester){
        return null;
    }

    @RequestMapping("/password/change")
    @ApiOperation(value="修改密码", httpMethod = "POST")
    public Responser 修改密码(
            @ApiParam(required = true, value = "密码修改")
            @RequestBody
            PasswordChangeRequester requester){
        return null;
    }

    @ApiOperation(value="用户信息修改", httpMethod = "POST")
    @RequestMapping("/info/change")
    public Responser 用户信息修改(
            @ApiParam(required = true, value = "信息修改")
            @RequestBody
            MemberCahngeRequester requester){
        return null;
    }

    @RequestMapping("/detail/{userName}")
    @ApiOperation(value="会员详情", httpMethod = "POST", response = MemberDetailResponse.class)
    @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", example = "18580255110")
    public Responser 会员详情(
            @PathVariable("userName")
            String userName){
        return null;
    }

    @RequestMapping("/adjust/course")
    @ApiOperation(value="调课申请", httpMethod = "POST")
    public Responser 调课申请(
            @ApiParam(required = true, value = "调课申请")
            @RequestBody
            AdjustApplyRequester requester){
        return null;
    }

    @RequestMapping("/adjust/class")
    @ApiOperation(value="转班申请", httpMethod = "POST")
    public Responser 转班申请(
            @ApiParam(required = true, value = "转班申请")
            @RequestBody
            AdjustApplyRequester requester){
        return null;
    }
}
