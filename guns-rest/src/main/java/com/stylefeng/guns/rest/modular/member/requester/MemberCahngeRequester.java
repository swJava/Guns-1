package com.stylefeng.guns.rest.modular.member.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 罗华.
 */
@ApiModel(value = "MemberCahngeRequester", description = "用户信息修改")
public class MemberCahngeRequester extends SimpleRequester {

    private static final long serialVersionUID = -8524227812098549736L;
    @ApiModelProperty(name = "userName", value = "用户名", required = true, position = 0, example = "18580255110")
    private String userName;
    @ApiModelProperty(name = "name", value = "用户姓名", position = 1, example = "李明")
    private String name;
    @ApiModelProperty(name = "gendar", value = "性别", position = 2, example = "1")
    private Integer gendar;
    @ApiModelProperty(name = "mobileNumber", value = "联系手机号", position = 3, example = "13399883934")
    private String mobileNumber;
    @ApiModelProperty(name = "address", value = "联系地址", position = 4, example = "重庆渝北区黄山大道中路55号")
    private String address;
    @ApiModelProperty(name = "qq", value = "QQ", position = 5, example = "181233")
    private String qq;
    @ApiModelProperty(name = "weixin", value = "微信号", position = 6, example = "dieej")
    private String weixin;
    @ApiModelProperty(name = "email", value = "邮箱", position = 7, example = "oeoe@163.com")
    private String email;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGendar() {
        return gendar;
    }

    public void setGendar(Integer gendar) {
        this.gendar = gendar;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean checkValidate() {
        return false;
    }
}
