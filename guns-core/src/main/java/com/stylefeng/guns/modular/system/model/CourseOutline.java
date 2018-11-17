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
 * 课程大纲
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-17
 */
@TableName("tb_course_outline")
public class CourseOutline extends Model<CourseOutline> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 课时编码： KS + 6位序列码
     */
    private String code;
    /**
     * 班级编码
     */
    @TableField("class_code")
    private String classCode;
    /**
     * 排课时间
     */
    @TableField("class_date")
    private Date classDate;
    /**
     * 上课时间：17:00-20:30
     */
    @TableField("class_time")
    private String classTime;
    /**
     * 大纲条目
     */
    private String outline;
    /**
     * 排序号
     */
    private Integer sort;
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

    public Date getClassDate() {
        return classDate;
    }

    public void setClassDate(Date classDate) {
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
