package com.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * <p>
 * 学生信息基础表
 * </p>
 *
 * @author simple.song
 * @since 2018-10-01
 */
@TableName("student_base")
public class StudentBase extends Model<StudentBase> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private Integer id;
    /**
     * 设备渠道区域编码
     */
    private String name;
    /**
     * 套餐编码
     */
    private String phone;
    /**
     * 账号类型:1=学生，2=教师
     */
    private int type;
    @TableField("time_create")
    private Date timeCreate;
    @TableField("time_update")
    private Date timeUpdate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
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
