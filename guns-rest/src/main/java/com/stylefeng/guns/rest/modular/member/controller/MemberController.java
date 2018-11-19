package com.stylefeng.guns.rest.modular.member.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.memberMGR.service.IMemberService;
import com.stylefeng.guns.modular.system.model.Attachment;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.service.IAttachmentService;
import com.stylefeng.guns.modular.system.service.ICaptchaService;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthResponse;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.education.requester.AdjustApplyRequester;
import com.stylefeng.guns.rest.modular.member.requester.LoginRequester;
import com.stylefeng.guns.rest.modular.member.requester.MemberCahngeRequester;
import com.stylefeng.guns.rest.modular.member.requester.PasswordChangeRequester;
import com.stylefeng.guns.rest.modular.member.requester.RegistRequester;
import com.stylefeng.guns.rest.modular.member.responser.MemberDetailResponse;
import com.stylefeng.guns.rest.modular.member.responser.RegistResponse;
import com.stylefeng.guns.util.PathUtil;
import com.sun.javafx.scene.shape.PathUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 认证
 * <p>
 * Created by 罗华.
 */
@Api(tags = "会员接口")
@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IMemberService memberService;

    @Autowired
    private ICaptchaService captchaService;

    @Autowired
    private IAttachmentService attachmentService;

    @Value("${application.attachment.visit-url}")
    private String attachmentVisitURL;

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册", httpMethod = "POST", response = RegistResponse.class)
    @ResponseBody
    public Responser regist(
            @ApiParam(required = true, value = "注册提交信息")
            @RequestBody
            @Valid
            RegistRequester requester) {

        String captcha = requester.getCaptcha();
        String userName = requester.getUserName();

        if (!captchaService.checkCaptcha(userName, captcha))
            throw new ServiceException(MessageConstant.MessageCode.SYS_CAPTCHA_NOT_MATCH);


        Member member = memberService.createMember(requester.getUserName(), requester.getPassword(), requester.toMap());
        return RegistResponse.me(member);
    }

    @RequestMapping("/login")
    @ApiOperation(value = "用户登录", httpMethod = "POST", response = AuthResponse.class)
    @ResponseBody
    public ResponseEntity<?> login(
            @ApiParam(required = true, value = "登录信息")
            @RequestBody
            LoginRequester requester) {

        int type = requester.getType();

        ResponseEntity<?> responseEntity = null;
        switch (type) {
            case 2:
                responseEntity = loginCaptcha(requester);
                break;
            case 1:
            default:
                responseEntity = loginNormal(requester);
        }

        return responseEntity;
    }

    private ResponseEntity<?> loginCaptcha(
            @ApiParam(required = true, value = "登录信息")
            @RequestBody
            LoginRequester requester) {

        String captcha = requester.getCaptcha();
        String userName = requester.getLoginCode();

        if (!captchaService.checkCaptcha(userName, captcha))
            throw new ServiceException(MessageConstant.MessageCode.SYS_CAPTCHA_NOT_MATCH);

        Wrapper<Member> queryWrapper = new EntityWrapper<Member>();
        queryWrapper.eq("user_name", userName);

        // 1 先找用户
        Member member = (Member) memberService.selectObj(queryWrapper);

        if (null == member)
            throw new ServiceException(MessageConstant.MessageCode.LOGIN_ACCOUNT_NOT_FOUND);
        // 2 用户状态
        int memState = member.getStatus();
        if (memState != 1)
            throw new ServiceException(MessageConstant.MessageCode.LOGIN_ACCOUNT_LOCKED);
        // 3 生成TOKEN
        final String randomKey = jwtTokenUtil.getRandomKey();
        final String token = jwtTokenUtil.generateToken(userName, randomKey);
        return ResponseEntity.ok(new AuthResponse(token, randomKey));
    }

    private ResponseEntity<?> loginNormal(
            @ApiParam(required = true, value = "登录信息")
            @RequestBody
            LoginRequester requester) {

        String captcha = requester.getCaptcha();
        String userName = requester.getLoginCode();

        if (!captchaService.checkCaptcha(userName, captcha))
            throw new ServiceException(MessageConstant.MessageCode.SYS_CAPTCHA_NOT_MATCH);

        Wrapper<Member> queryWrapper = new EntityWrapper<Member>();
        queryWrapper.eq("user_name", userName);

        // 1 先找用户
        Member member = (Member) memberService.selectById((Long) memberService.selectObj(queryWrapper));

        if (null == member)
            throw new ServiceException(MessageConstant.MessageCode.LOGIN_ACCOUNT_NOT_FOUND);

        // 2 验证密码
        String encryptPassword = member.getPassword();
        String inputPassword = requester.getPassword();

        if (null == encryptPassword)
            throw new ServiceException(MessageConstant.MessageCode.LOGIN_FAILED);
        if (null == inputPassword)
            throw new ServiceException(MessageConstant.MessageCode.LOGIN_FAILED);

        String inputEncryptPassword = new Sha256Hash(inputPassword).toHex().toUpperCase();

        if (!encryptPassword.equalsIgnoreCase(inputEncryptPassword))
            throw new ServiceException(MessageConstant.MessageCode.LOGIN_FAILED);

        int memState = member.getStatus();
        if (memState != 1)
            throw new ServiceException(MessageConstant.MessageCode.LOGIN_ACCOUNT_LOCKED);
        // 3 生成TOKEN
        final String randomKey = jwtTokenUtil.getRandomKey();
        final String token = jwtTokenUtil.generateToken(userName, randomKey);
        return ResponseEntity.ok(new AuthResponse(token, randomKey));
    }

    @RequestMapping("/password/change")
    @ApiOperation(value = "修改密码", httpMethod = "POST")
    @ResponseBody
    public Responser 修改密码(
            @ApiParam(required = true, value = "密码修改")
            @RequestBody
            PasswordChangeRequester requester) {

        return null;
    }

    @ApiOperation(value = "用户信息修改", httpMethod = "POST")
    @RequestMapping("/info/change")
    @ResponseBody
    public Responser 用户信息修改(
            @ApiParam(required = true, value = "信息修改")
            @RequestBody
            MemberCahngeRequester requester) {
        return null;
    }

    @RequestMapping("/detail/{userName}")
    @ApiOperation(value = "会员详情", httpMethod = "POST", response = MemberDetailResponse.class)
    @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", example = "18580255110")
    @ResponseBody
    public Responser detail(
            @PathVariable("userName")
            String userName) {

        Wrapper<Member> queryWrapper = new EntityWrapper<Member>();
        queryWrapper.eq("user_name", userName);

        Member member = memberService.selectOne(queryWrapper);

        if (null == member)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        Wrapper<Attachment> attachmentQueryWrapper = new EntityWrapper<Attachment>();
        attachmentQueryWrapper.eq("master_name", "MEMBER_AVATAR");
        attachmentQueryWrapper.eq("master_code", member.getUserName());

        Attachment memberAvatar = attachmentService.selectOne(attachmentQueryWrapper);
        if (null == memberAvatar)
            memberAvatar = attachmentService.selectById(1L);

        return MemberDetailResponse.me(member).addAvatar(PathUtil.generate(attachmentVisitURL, String.valueOf(memberAvatar.getId())));
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
