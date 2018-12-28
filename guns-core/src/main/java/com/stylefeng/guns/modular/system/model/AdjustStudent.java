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
 * 调课/班申请表(学生)
 * </p>
 *
 * @author simple.song
 * @since 2018-11-19
 */
@TableName("tb_adjust_student")
public class AdjustStudent extends Model<AdjustStudent> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 申请用户名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 学生编码（学号）
     */
    @TableField("student_code")
    private String studentCode;
    /**
     * 类型： 1 调课   2  调班
     */
    private Integer type;
    /**
     * 调整课时编码（只在调课申请时使用）
     */
    @TableField("outline_code")
    private String outlineCode;
    /**
     * 调出班级编码
     */
    @TableField("source_class")
    private String sourceClass;
    /**
     * 调入班级编码
     */
    @TableField("target_class")
    private String targetClass;
    /**
     * 状态： 0 禁用  1启用
     */
    private Integer status;
    /**
     * 流程状态： 10 新建 11 通过 12 打回 13 关闭
     */
    @TableField("work_status")
    private Integer workStatus;
    /**
     * 备注
     */
    private String remark;
    /**
     * 操作员ID
     */
    @TableField("op_id")
    private Long opId;
    /**
     * 操作员
     */
    private String operator;

    /**
     * 申请时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 审核时间
     */
    @TableField("update_time")
    private Date updateTime;


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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOutlineCode() {
        return outlineCode;
    }

    public void setOutlineCode(String outlineCode) {
        this.outlineCode = outlineCode;
    }

    public String getSourceClass() {
        return sourceClass;
    }

    public void setSourceClass(String sourceClass) {
        this.sourceClass = sourceClass;
    }

    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOpId() {
        return opId;
    }

    public void setOpId(Long opId) {
        this.opId = opId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AdjustStudent{" +
        "id=" + id +
        ", studentCode=" + studentCode +
        ", type=" + type +
        ", userName=" + userName +
        ", outlineCode=" + outlineCode +
        ", sourceClass=" + sourceClass +
        ", targetClass=" + targetClass +
        ", status=" + status +
        ", workStatus=" + workStatus +
        ", remark=" + remark +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
