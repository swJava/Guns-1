package com.stylefeng.guns.rest.modular.member.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.member.MemberStarEnum;
import com.stylefeng.guns.modular.member.MemberStateEnum;
import com.stylefeng.guns.modular.memberMGR.service.IMemberService;
import com.stylefeng.guns.modular.system.model.Attachment;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.service.IAttachmentService;
import com.stylefeng.guns.modular.system.service.ICaptchaService;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
        String number = requester.getNumber();

        if (!captchaService.checkCaptcha(number, captcha))
            throw new ServiceException(MessageConstant.MessageCode.SYS_CAPTCHA_NOT_MATCH);

        Map<String, Object> extraParams = requester.toMap();
        extraParams.put("star", MemberStarEnum.Star_1.code);
        Member member = memberService.createMember(userName, requester.getPassword(), extraParams);
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
                // 手机号码直接登录
                responseEntity = loginCaptcha(requester);
                break;
            case 1:
            default:
                // 用户名、密码登录
                responseEntity = loginNormal(requester);
        }

        return responseEntity;
    }

    private ResponseEntity<?> loginCaptcha( LoginRequester requester ) {

        String captcha = requester.getCaptcha();
        String number = requester.getLoginCode();

        if (!captchaService.checkCaptcha(number, captcha))
            throw new ServiceException(MessageConstant.MessageCode.SYS_CAPTCHA_NOT_MATCH);

        Wrapper<Member> queryWrapper = new EntityWrapper<Member>();
        queryWrapper.eq("mobile_number", number);
        queryWrapper.ne("status", MemberStateEnum.Invalid.code);

        // 1 先找用户
        Member member = (Member) memberService.selectOne(queryWrapper);

        if (null == member){
            String username = memberService.generateUserName();
            Member existMember = memberService.selectOne(new EntityWrapper<Member>().eq("user_name", username));
            while(null != existMember){
                username = memberService.generateUserName();
                existMember = memberService.selectOne(new EntityWrapper<Member>().eq("user_name", username));
            }
            Map<String, Object> extraParams = new HashMap<String, Object>();
            extraParams.put("number", number);
            extraParams.put("star", MemberStarEnum.Star_0);
            member = memberService.createMember(username, extraParams);
        }

        // 2 生成TOKEN
        final String randomKey = jwtTokenUtil.getRandomKey();
        final String token = jwtTokenUtil.generateToken(member.getUserName(), randomKey);
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
        Member member = memberService.selectOne(queryWrapper);

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
    public Responser changePassword(
            @ApiParam(required = true, value = "密码修改")
            @RequestBody
            PasswordChangeRequester requester) {

        String captcha = requester.getCaptcha();
        String userName = requester.getUserName();

        if (!captchaService.checkCaptcha(userName, captcha))
            throw new ServiceException(MessageConstant.MessageCode.SYS_CAPTCHA_NOT_MATCH);

        Wrapper<Member> queryWrapper = new EntityWrapper<Member>();
        queryWrapper.eq("user_name", userName);
        queryWrapper.ne("status", MemberStateEnum.Invalid.code);

        Member existMember = memberService.selectOne(queryWrapper);

        if (null == existMember)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{requester.getUserName()});

        memberService.changePassword(userName, requester.getPassword());

        return SimpleResponser.success();
    }

    @ApiOperation(value = "用户信息修改", httpMethod = "POST")
    @RequestMapping(value = "/info/change", method = RequestMethod.POST)
    @ResponseBody
    public Responser changeInfo(
            @ApiParam(required = true, value = "信息修改")
            @RequestBody
            MemberCahngeRequester requester) {

        Wrapper<Member> queryWrapper = new EntityWrapper<Member>();
        queryWrapper.eq("user_name", requester.getUserName());

        Member existMember = memberService.selectOne(queryWrapper);

        if (null == existMember)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        String[] ignoreProperties = new String[]{"id", "userName", "joinDate"};
        BeanUtils.copyProperties(requester, existMember);
        // 更新操作的ServiceException异常直接抛出，由全局异常处理器处理返回
        memberService.updateById(existMember);

        return SimpleResponser.success();
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

}
