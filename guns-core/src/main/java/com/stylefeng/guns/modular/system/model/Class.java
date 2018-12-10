package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 班级
 * </p>
 *
 * @author simple
 * @since 2018-12-09
 */
@TableName("tb_class")
@ApiModel(value = "Class", description = "班级")
public class Class extends Model<Class> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;
    /**
     * 班级编码： BJ+6位序列码
     */
    @ApiModelProperty(name = "code", value = "编码", position = 0, example="BJ000001")
    private String code;
    /**
     * 班级名称
     */
    @ApiModelProperty(name = "name", value = "班级名称", position = 1, example="小学一年级数据入门班")
    private String name;

    /**
     * 学期
     */
    @ApiModelProperty(name = "cycle", value = "学期", position = 2, example="2")
    private Integer cycle;

    /**
     * 班次
     */
    @ApiModelProperty(name = "ability", value = "班次", position = 3, example="4")
    private Integer ability;

    /**
     * 学期: 1 春季班； 2 秋季班； 3 寒假班； 4 短期班； 99 活动类
     */
    private Integer cycle;
    /**
     * 班次： 1 启航； 2 敏学； 3 勤思； 4 创新； 5 诊断； 99 其他
     */
    private Integer ability;
    /**
     * 开课起始日期
     */
    @TableField("begin_date")
    @ApiModelProperty(name = "beginDate", value = "开课起始日期", position = 0, example="4")
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
<<<<<<< HEAD
     * 价格
=======
     * 价格： 单位： 分
>>>>>>> 44eb44aecc64acebd68cd4d278dfa42de93bf9c6
     */
    private Long price;
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
     * 主讲教师名称
     */
    private String teacher;
    /**
     * 辅导教师编码
     */
    @TableField("teacher_second_code")
    private String teacherSecondCode;
    /**
     * 辅导教室名称
     */
    @TableField("teacher_second")
    private String teacherSecond;

    /**
     * 辅导教师名称
     */
    @TableField("teacher_second")
    private String teacherSecond;


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

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Integer getAbility() {
        return ability;
    }

    public void setAbility(Integer ability) {
        this.ability = ability;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacherSecondCode() {
        return teacherSecondCode;
    }

    public void setTeacherSecondCode(String teacherSecondCode) {
        this.teacherSecondCode = teacherSecondCode;
    }

    public String getTeacherSecond() {
        return teacherSecond;
    }

    public void setTeacherSecond(String teacherSecond) {
        this.teacherSecond = teacherSecond;
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
        ", cycle=" + cycle +
        ", ability=" + ability +
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
        ", price=" + price +
        ", quato=" + quato +
        ", signEndDate=" + signEndDate +
        ", status=" + status +
        ", teacherCode=" + teacherCode +
        ", teacher=" + teacher +
        ", teacherSecondCode=" + teacherSecondCode +
        ", teacherSecond=" + teacherSecond +
        "}";
    }
}
