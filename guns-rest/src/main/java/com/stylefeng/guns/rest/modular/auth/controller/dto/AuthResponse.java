package com.stylefeng.guns.rest.modular.auth.controller.dto;

import java.io.Serializable;

import com.stylefeng.guns.rest.modular.member.responser.MemberExt;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 认证的响应结果
 *
 * @author fengshuonan
 * @Date 2017/8/24 13:58
 */
@ApiModel(value = "AuthResponse", description = "用户登陆返回")
public class AuthResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    /**
     * jwt token
     */
    @ApiModelProperty(name = "token", value = "令牌")
    private final String token;

    @ApiModelProperty(name = "member", value = "用户")
    private MemberExt member;

    /**
     * 用于客户端混淆md5加密
     */
    @ApiModelProperty(name = "token", value = "混淆码")
    private final String salt;

    public AuthResponse(String token, String salt) {
        this.token = token;
        this.salt = salt;
    }

    public String getToken() {
        return this.token;
    }

    public String getSalt() {
        return salt;
    }

    public void setMember(MemberExt member) {
        this.member = member;
    }

    public MemberExt getMember() {
        return member;
    }
}
