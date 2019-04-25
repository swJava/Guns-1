package com.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 会员认证信息
 * </p>
 *
 * @author simple
 * @since 2018-11-03
 */
@TableName("tb_member_auth")
public class MemberAuth extends Model<MemberAuth> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 登录次数
     */
    @TableField("login_count")
    private Integer loginCount;
    /**
     * 最近登录时间
     */
    @TableField("last_login_date")
    private Date lastLoginDate;
    /**
     * 上次密码修改时间
     */
    @TableField("last_chgpasswd_date")
    private Date lastChgpasswdDate;
    /**
     * 登录错误次数
     */
    @TableField("error_login_count")
    private Integer errorLoginCount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getLastChgpasswdDate() {
        return lastChgpasswdDate;
    }

    public void setLastChgpasswdDate(Date lastChgpasswdDate) {
        this.lastChgpasswdDate = lastChgpasswdDate;
    }

    public Integer getErrorLoginCount() {
        return errorLoginCount;
    }

    public void setErrorLoginCount(Integer errorLoginCount) {
        this.errorLoginCount = errorLoginCount;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MemberAuth{" +
        "id=" + id +
        ", username=" + username +
        ", loginCount=" + loginCount +
        ", lastLoginDate=" + lastLoginDate +
        ", lastChgpasswdDate=" + lastChgpasswdDate +
        ", errorLoginCount=" + errorLoginCount +
        "}";
    }
}
