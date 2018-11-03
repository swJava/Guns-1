package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 学员教学计划
 * </p>
 *
 * @author simple
 * @since 2018-11-03
 */
@TableName("tb_schedule_student")
public class ScheduleStudent extends Model<ScheduleStudent> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    private Long id;
    /**
     * 学员编码
     */
    @TableField("student_code")
    private String studentCode;
    /**
     * 班级编码
     */
    @TableField("class_code")
    private String classCode;
    /**
     * 课时编码
     */
    @TableField("outline_code")
    private String outlineCode;
    /**
     * 状态： -1  已失效  0  未上课   1  已完成
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ScheduleStudent{" +
        "id=" + id +
        ", studentCode=" + studentCode +
        ", classCode=" + classCode +
        ", outlineCode=" + outlineCode +
        ", status=" + status +
        ", remark=" + remark +
        "}";
    }
}
