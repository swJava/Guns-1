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
 * 课程大纲
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-17
 */
@TableName("tb_schedule_class")
@ApiModel(value = "ScheduleClass", description = "开班计划")
public class ScheduleClass extends Model<ScheduleClass> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;
    /**
     * 班级编码
     */
    @TableField("class_code")
    @ApiModelProperty(name = "classCode", value = "班级编码", example = "BJ000001")
    private String classCode;
    /**
     * 课时编码
     */
    @TableField("outline_code")
    @ApiModelProperty(name = "outlineCode", value = "课时编码", example = "KS000001")
    private String outlineCode;
    /**
     * 上课日期
     */
    @TableField("study_date")
    @ApiModelProperty(name = "classDate", value = "上课日期", example = "2018-11-27")
    private Date classDate;

    /**
     * 星期
     */
    @TableField("week")
    @ApiModelProperty(name = "week", value = "星期， 1-7", example = "7")
    private Integer week;
    /**
     * 上课时间：17:00-20:30
     */
    @TableField("class_time")
    @ApiModelProperty(name = "classTime", value = "上课时间", example = "9点 0900")
    private String classTime;
    /**
     * 上课时间：17:00-20:30
     */
    @TableField("end_time")
    @ApiModelProperty(name = "endTime", value = "下课时间", example = "20点15 2015")
    private String endTime;
    /**
     * 大纲条目
     */
    @ApiModelProperty(name = "outline", value = "大纲条目", example = "第一课")
    private String outline;
    /**
     * 排序号
     */
    @ApiModelProperty(name = "sort", value = "排序号", example = "1")
    private Integer sort;
    /**
     * 状态
     */
    @ApiModelProperty(name = "status", value = "状态", example = "1")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getOutlineCode() {
        return outlineCode;
    }

    public void setOutlineCode(String outlineCode) {
        this.outlineCode = outlineCode;
    }

    public Date getClassDate() {
        return classDate;
    }

    public void setClassDate(Date classDate) {
        this.classDate = classDate;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
        return "CourseOutline{" +
        "id=" + id +
        ", classCode=" + classCode +
        ", outlineCode=" + outlineCode +
        ", classDate=" + classDate +
        ", week=" + week +
        ", classTime=" + classTime +
        ", endTime=" + endTime +
        ", outline=" + outline +
        ", sort=" + sort +
        ", status=" + status +
        "}";
    }
}
