package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 购课单
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-15
 */
@ApiModel(value = "CourseCart", description = "选课单")
@TableName("tb_course_cart")
public class CourseCart extends Model<CourseCart> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;

    /**
     * 编码
     */
    @ApiModelProperty(name = "code", value = "编码", position = 0, example="CC18122500000001")
    private String code;
    /**
     * 用户名
     */
    @TableField("user_name")
    @ApiModelProperty(name = "userName", value = "用户名", position = 1, example="18580255110")
    private String userName;
    /**
     * 学员编码
     */
    @TableField("student_code")
    @ApiModelProperty(name = "studentCode", value = "学员", position = 2, example="XY181225000001")
    private String studentCode;
    /**
     * 学员名称
     */
    @ApiModelProperty(name = "student", value = "学员名称", position = 3, example="小明")
    private String student;
    /**
     * 购课时间
     */
    @TableField("join_date")
    @ApiModelProperty(name = "joinDate", value = "购课时间", position = 4, example="2018-12-25")
    private Date joinDate;
    /**
     * 课程名称
     */
    @TableField("course_name")
    @ApiModelProperty(name = "courseName", value = "课程名称", position = 5, example="小学三年级语文")
    private String courseName;
    /**
     * 班级编码
     */
    @TableField("class_code")
    @ApiModelProperty(name = "classCode", value = "班级编码", position = 5, example="BJ000001")
    private String classCode;
    /**
     * 班级名称（文字描述）
     */
    @TableField("class_name")
    @ApiModelProperty(name = "className", value = "班级名称", position = 6, example="小学三年级语文入门班")
    private String className;
    /**
     * 教学方式（文字描述）
     */
    @TableField("class_method")
    @ApiModelProperty(name = "classMethod", value = "教学方式（文字描述）", position = 7, example="面授")
    private String classMethod;
    /**
     * 教学时间（文字描述）
     */
    @TableField("class_time")
    @ApiModelProperty(name = "classTime", value = "教学时间（文字描述）", position = 8, example="每周三 09:00 ~ 11:00")
    private String classTime;
    /**
     * 教室（文字描述）
     */
    @ApiModelProperty(name = "classroom", value = "教室（文字描述）", position = 9, example="科萃大厦 4 楼 402教室")
    private String classroom;
    /**
     * 讲师
     */
    @ApiModelProperty(name = "teacher", value = "讲师（文字描述）", position = 10, example="马云")
    private String teacher;
    /**
     * 辅导员
     */
    @ApiModelProperty(name = "assister", value = "辅导员（文字描述）", position = 10, example="马云")
    private String assister;
    /**
     * 状态   -1 失效  0 新增  1 已生成订单
     */
    @ApiModelProperty(name = "status", value = "状态 -1 失效  0 新增  1 已生成订单", position = 10, example="0")
    private Integer status;
    /**
     * 金额, 单位： 分
     */
    @ApiModelProperty(name = "amount", value = "金额， 单位： 分", position = 10, example="2300")
    private Long amount;


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
        ", code=" + code +
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
