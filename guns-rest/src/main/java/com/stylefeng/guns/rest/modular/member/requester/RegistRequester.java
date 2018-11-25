package com.stylefeng.guns.rest.modular.member.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

/**
 * Created by 罗华.
 */
@ApiModel(value = "RegistRequester", description = "注册信息")
public class RegistRequester extends SimpleRequester {

    private static final long serialVersionUID = -3364653634237690757L;
    @ApiModelProperty(name = "userName", value = "用户名", required = true, position = 0, example = "luohua")
    @NotBlank(message = "用户名不能为空")
    @Size(min = 6, max = 32, message = "用户名长度只能是6 - 32 位字符")
    @Pattern(regexp = "^[A-Za-z0-9_]+$", message = "用户名不合法，只能是数字、英文字符")
    private String userName;

    @ApiModelProperty(name = "number", value = "手机号码", required = true, position = 0, example = "18580255110")
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0,6,7,8])|(18[0-9]))\\d{8}$", message = "手机号码不合法")
    private String number;

    @ApiModelProperty(name = "password", value = "密码", required = true, position = 1, example = "abcd1234")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(name = "captcha", value = "验证码", required = true, position = 2, example = "2345")
    @NotBlank(message = "验证码不能为空")
    @Pattern(regexp = "^[0-9]+$")
    private String captcha;

    @ApiModelProperty(name = "grade", value = "学员所入年级", required = true, position = 3, example = "4")
    @Min(value = 0, message = "grade 最小不能小于0")
    @Max(value = 20, message = "grade 最大不能超过20")
    private Integer grade;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public boolean checkValidate() {
        return false;
    }
}
