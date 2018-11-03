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
 * 班级
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-03
 */
@TableName("tb_class")
public class Class extends Model<Class> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 班级编码： BJ+6位序列码
     */
    private String code;
    /**
     * 班级名称
     */
    private String name;
    /**
     * 开课起始日期
     */
    @TableField("begin_date")
    private Date beginDate;
    /**
     * 开课结束日期
     */
    @TableField("end_date")
    private Date endDate;
    /**
     * 开课时间类型： 5 DAY_OF_MONTH ； 7 DAY_OF_WEEK
     */
    @TableField("study_time_type")
    private Integer studyTimeType;
    /**
     * 开课时间
     */
    @TableField("study_time_value")
    private String studyTimeValue;
    /**
     * 开始时间
     */
    @TableField("begin_time")
    private String beginTime;
    /**
     * 结束时间
     */
    @TableField("end_time")
    private String endTime;
    /**
     * 单位：分钟
     */
    private Integer duration;
    /**
     * 总课时数
     */
    private Integer period;
    /**
     * 教室编码
     */
    @TableField("class_room_code")
    private String classRoomCode;
    /**
     * 教室
     */
    @TableField("class_room")
    private String classRoom;
    /**
     * 教授课程
     */
    @TableField("course_code")
    private String courseCode;
    /**
     * 课程名称
     */
    @TableField("course_name")
    private String courseName;
    /**
     * 关注度
     */
    private Integer star;
    /**
     * 剩余报名人数
     */
    private Integer quato;
    /**
     * 报名截止时间
     */
    @TableField("sign_end_date")
    private Date signEndDate;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 主讲教师编码
     */
    @TableField("teacher_code")
    private String teacherCode;
    /**
     * 辅导教师编码
     */
    @TableField("teacher_second_code")
    private String teacherSecondCode;


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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getStudyTimeType() {
        return studyTimeType;
    }

    public void setStudyTimeType(Integer studyTimeType) {
        this.studyTimeType = studyTimeType;
    }

    public String getStudyTimeValue() {
        return studyTimeValue;
    }

    public void setStudyTimeValue(String studyTimeValue) {
        this.studyTimeValue = studyTimeValue;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getClassRoomCode() {
        return classRoomCode;
    }

    public void setClassRoomCode(String classRoomCode) {
        this.classRoomCode = classRoomCode;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getQuato() {
        return quato;
    }

    public void setQuato(Integer quato) {
        this.quato = quato;
    }

    public Date getSignEndDate() {
        return signEndDate;
    }

    public void setSignEndDate(Date signEndDate) {
        this.signEndDate = signEndDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public String getTeacherSecondCode() {
        return teacherSecondCode;
    }

    public void setTeacherSecondCode(String teacherSecondCode) {
        this.teacherSecondCode = teacherSecondCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Class{" +
        "id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", beginDate=" + beginDate +
        ", endDate=" + endDate +
        ", studyTimeType=" + studyTimeType +
        ", studyTimeValue=" + studyTimeValue +
        ", beginTime=" + beginTime +
        ", endTime=" + endTime +
        ", duration=" + duration +
        ", period=" + period +
        ", classRoomCode=" + classRoomCode +
        ", classRoom=" + classRoom +
        ", courseCode=" + courseCode +
        ", courseName=" + courseName +
        ", star=" + star +
        ", quato=" + quato +
        ", signEndDate=" + signEndDate +
        ", status=" + status +
        ", teacherCode=" + teacherCode +
        ", teacherSecondCode=" + teacherSecondCode +
        "}";
    }
}
