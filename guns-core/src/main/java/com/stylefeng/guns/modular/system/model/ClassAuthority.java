package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 班级报名权限关系表
 * </p>
 *
 * @author simpleSong
 * @since 2019-07-30
 */
@TableName("tb_class_authority")
public class ClassAuthority extends Model<ClassAuthority> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 班级编码
     */
    @TableField("class_code")
    private String classCode;
    /**
     * 班级名称
     */
    @TableField("class_name")
    private String className;
    /**
     * 学生编码
     */
    @TableField("student_code")
    private String studentCode;
    /**
     * 学生名称
     */
    @TableField("student_name")
    private String studentName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ClassAuthority{" +
        "id=" + id +
        ", classCode=" + classCode +
        ", className=" + className +
        ", studentCode=" + studentCode +
        ", studentName=" + studentName +
        "}";
    }
}
