package com.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 排课表
 * </p>
 *
 * @author simple
 * @since 2018-11-03
 */
@TableName("tb_schedule")
public class Schedule extends Model<Schedule> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    private Long id;
    /**
     * 课程表编码
     */
    private String code;
    /**
     * 教师编码
     */
    @TableField("teacher_code")
    private String teacherCode;
    /**
     * 教师
     */
    private String teacher;
    /**
     * 课程编码
     */
    @TableField("course_code")
    private String courseCode;
    /**
     * 课程
     */
    private String course;
    /**
     * 班级编码
     */
    @TableField("class_code")
    private String classCode;
    /**
     * 班级
     */
    @TableField("class_Name")
    private String className;
    /**
     * 教室编码
     */
    @TableField("classroom_code")
    private String classroomCode;
    /**
     * 教室
     */
    private String classroom;
    /**
     * 上课时间
     */
    @TableField("study_date")
    private Date studyDate;
    private String remark;
    /**
     * 状态 : 0 无效  1  有效   2  已转出
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

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
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

    public String getClassroomCode() {
        return classroomCode;
    }

    public void setClassroomCode(String classroomCode) {
        this.classroomCode = classroomCode;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Date getStudyDate() {
        return studyDate;
    }

    public void setStudyDate(Date studyDate) {
        this.studyDate = studyDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return "Schedule{" +
        "id=" + id +
        ", code=" + code +
        ", teacherCode=" + teacherCode +
        ", teacher=" + teacher +
        ", courseCode=" + courseCode +
        ", course=" + course +
        ", classCode=" + classCode +
        ", class=" + className +
        ", classroomCode=" + classroomCode +
        ", classroom=" + classroom +
        ", studyDate=" + studyDate +
        ", remark=" + remark +
        ", status=" + status +
        "}";
    }
}
