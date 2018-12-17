package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 课程大纲
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-17
 */
@TableName("tb_course_outline")
@ApiModel(value = "CourseOutline", description = "课时")
public class CourseOutline extends Model<CourseOutline> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;
    /**
     * 课时编码： KS + 6位序列码
     */
    @ApiModelProperty(name = "code", value = "课时编码", example = "KS000001")
    private String code;
    /**
     * 班级编码
     */
    @TableField("class_code")
    @ApiModelProperty(name = "classCode", value = "班级编码", example = "BJ000001")
    private String classCode;
    /**
     * 上课日期
     */
    @TableField("class_date")
    @ApiModelProperty(name = "classDate", value = "上课日期", example = "2018-11-27 周三")
    private String classDate;
    /**
     * 上课时间：17:00-20:30
     */
    @TableField("class_time")
    @ApiModelProperty(name = "classTime", value = "上课时间", example = "17:00-20:30")
    private String classTime;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassDate() {
        return classDate;
    }

    public void setClassDate(String classDate) {
        this.classDate = classDate;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
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
        ", code=" + code +
        ", classCode=" + classCode +
        ", classDate=" + classDate +
        ", classTime=" + classTime +
        ", outline=" + outline +
        ", sort=" + sort +
        ", status=" + status +
        "}";
    }
}
