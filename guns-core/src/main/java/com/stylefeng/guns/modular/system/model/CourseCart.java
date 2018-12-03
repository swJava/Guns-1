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
 * 购课单
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-15
 */
@TableName("tb_course_cart")
public class CourseCart extends Model<CourseCart> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 学员编码
     */
    @TableField("student_code")
    private String studentCode;
    /**
     * 学员名称
     */
    private String student;
    /**
     * 购课时间
     */
    @TableField("join_date")
    private Date joinDate;
    /**
     * 课程名称
     */
    @TableField("course_name")
    private String courseName;
    /**
     * 班级编码
     */
    @TableField("class_code")
    private String classCode;
    /**
     * 班级名称（文字描述）
     */
    @TableField("class_name")
    private String className;
    /**
     * 教学方式（文字描述）
     */
    @TableField("class_method")
    private String classMethod;
    /**
     * 教学时间（文字描述）
     */
    @TableField("class_time")
    private String classTime;
    /**
     * 教室（文字描述）
     */
    private String classroom;
    /**
     * 讲师
     */
    private String teacher;
    /**
     * 辅导员
     */
    private String assister;
    /**
     * 状态   -1 失效  0 新增  1 有效 
     */
    private Integer status;
    /**
     * 金额, 单位： 分
     */
    private Long amount;


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

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getAssister() {
        return assister;
    }

    public void setAssister(String assister) {
        this.assister = assister;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CourseCart{" +
        "id=" + id +
        ", userName=" + userName +
        ", studentCode=" + studentCode +
        ", student=" + student +
        ", joinDate=" + joinDate +
        ", courseName=" + courseName +
        ", classCode=" + classCode +
        ", className=" + className +
        ", classMethod=" + classMethod +
        ", classTime=" + classTime +
        ", classroom=" + classroom +
        ", teacher=" + teacher +
        ", assister=" + assister +
        ", status=" + status +
        ", amount=" + amount +
        "}";
    }
}
