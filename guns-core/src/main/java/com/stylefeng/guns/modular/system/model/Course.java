package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 课程表
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-25
 */
@TableName("tb_course")
public class Course extends Model<Course> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 课程编码： KC + 6位序列码
     */
    private String code;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 授课方式： 1 面授； 2 在线； 3 双师
     */
    private Integer method;
    /**
     * 所属学科
     */
    private String subject;
    /**
     * 年级
     */
    private Integer grade;
    /**
     * 课时长
     */
    private Integer period;
    /**
     * 课程介绍
     */
    private String description;
    /**
     * 状态
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Course{" +
        "id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", method=" + method +
        ", subject=" + subject +
        ", period=" + period +
        ", description=" + description +
        ", status=" + status +
        "}";
    }
}
