package com.stylefeng.guns.rest.modular.member.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 罗华.
 */
@ApiModel(value = "LoginRequester", description = "用户登录")
public class LoginRequester extends SimpleRequester {
    private static final long serialVersionUID = 7901218959632021907L;
    /**
     * 登录类型
     */
    @ApiModelProperty(name = "type", value = "登录类型: 1 用户名 密码登陆 2 手机动态验证码登陆", required = true, position = 0, example = "1")
    private Integer type;

    /**
     * 登录码
     */
    @ApiModelProperty(name = "loginCode", value = "登录码", required = true, position = 1, example = "18580255110")
    private String loginCode;

    /**
     * 登录密码
     */
    @ApiModelProperty(name = "password", value = "登录密码", required = false, position = 2, example = "e9cee71ab932fde863338d08be4de9dfe39ea049bdafb342ce659ec5450b69ae")
    private String password;

    /**
     * 验证码
     */
    @ApiModelProperty(name = "captcha", value = "验证码", required = true, position = 3, example = "ge2x0")
    private String captcha;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public boolean checkValidate() {
        return false;
    }
}
