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
    @TableField("course_code")
    @ApiModelProperty(name = "courseCode", value = "课程编码", example = "KC000001")
    private String courseCode;
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

    @ApiModelProperty(name = "description", value = "简介", example = "第一课： 自我介绍")
    private String description;

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

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        ", courseCode=" + courseCode +
        ", outline=" + outline +
        ", sort=" + sort +
        ", status=" + status +
        ", description=" + description +
        "}";
    }
}
