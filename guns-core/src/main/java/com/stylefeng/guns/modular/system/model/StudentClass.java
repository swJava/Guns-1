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
 * 学员报班信息表
 * </p>
 *
 * @author 罗华
 * @since 2018-12-16
 */
@TableName("tb_student_class")
public class StudentClass extends Model<StudentClass> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 班级编码
     */
    @TableField("class_name")
    private String className;
    /**
     * 已完成课时数
     */
    private Integer period;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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
        ", className=" + className +
        ", period=" + period +
        ", status=" + status +
        "}";
    }
}
