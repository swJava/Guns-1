package com.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author simple
 * @since 2018-11-03
 */
@TableName("tb_member")
public class Member extends Model<Member> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键标示
     */
    private Long id;
    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 密码sha256加密
     */
    private String password;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 性别: 1 男  2 女
     */
    private Integer gender;
    /**
     * 联系手机
     */
    @TableField("mobile_number")
    private String mobileNumber;
    /**
     * 联系地址
     */
    private String address;
    /**
     * QQ号码
     */
    private String qq;
    /**
     * 微信号
     */
    private String weiixin;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 状态： 1 0 无效 11 有效 1 2 锁定 
     */
    private Integer status;
    /**
     * 加入时间
     */
    @TableField("join_date")
    private Date joinDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
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

    public String getWeiixin() {
        return weiixin;
    }

    public void setWeiixin(String weiixin) {
        this.weiixin = weiixin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Member{" +
        "id=" + id +
        ", userName=" + userName +
        ", password=" + password +
        ", name=" + name +
        ", gender=" + gender +
        ", mobileNumber=" + mobileNumber +
        ", address=" + address +
        ", qq=" + qq +
        ", weiixin=" + weiixin +
        ", email=" + email +
        ", status=" + status +
        ", joinDate=" + joinDate +
        "}";
    }
}
