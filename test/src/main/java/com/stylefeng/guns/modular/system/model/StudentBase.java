package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 学生信息基础表
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-07
 */
@TableName("student_base")
public class StudentBase extends Model<StudentBase> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 姓名
     */
    private Integer name;
    /**
     * 手机号
     */
    private Boolean phone;
    /**
     * 账号类型:1=学生，2=教师
     */
    private Integer type;
    /**
     * 创建时间
     */
    @TableField("time_create")
    private Date timeCreate;
    /**
     * 更新时间
     */
    @TableField("time_update")
    private Date timeUpdate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Boolean getPhone() {
        return phone;
    }

    public void setPhone(Boolean phone) {
        this.phone = phone;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Date getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Date timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StudentBase{" +
        "id=" + id +
        ", name=" + name +
        ", phone=" + phone +
        ", type=" + type +
        ", timeCreate=" + timeCreate +
        ", timeUpdate=" + timeUpdate +
        "}";
    }
}
