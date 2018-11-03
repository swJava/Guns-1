package com.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 学员报班信息表
 * </p>
 *
 * @author simple
 * @since 2018-11-03
 */
@TableName("tb_student_class")
public class StudentClass extends Model<StudentClass> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    private Long id;
    /**
     * 学员编码
     */
    @TableField("student_code")
    private String studentCode;
    /**
     * 班级编码
     */
    @TableField("class_code")
    private String classCode;
    /**
     * 已完成课时数
     */
    private Integer period;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 上课时间
     */
    private Date datetime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StudentClass{" +
        "id=" + id +
        ", studentCode=" + studentCode +
        ", classCode=" + classCode +
        ", period=" + period +
        ", status=" + status +
        ", datetime=" + datetime +
        "}";
    }
}
