package com.stylefeng.guns.rest.modular.member.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Created by 罗华.
 */
@ApiModel(value = "PasswordChangeRequester", description = "用户密码修改")
public class PasswordChangeRequester extends SimpleRequester {
    @ApiModelProperty(name = "userName", value = "用户名", required = true, position = 0, example = "18580255110")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(name = "captcha", value = "验证码", required = true, position = 1, example = "1234")
    @NotBlank(message = "验证码不能为空")
    private String captcha;

    @ApiModelProperty(name = "password", value = "新密码", required = true, position = 2, example = "newpassword")
    @NotBlank(message = "密码不能为空")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean checkValidate() {
        return false;
    }
}
