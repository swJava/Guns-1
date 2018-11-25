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
     * 当前班级编码
     */
    @TableField("class_code")
    private String classCode;
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
     * 申请用户名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 调入目标编码
     */
    private String target;
    /**
     * 状态： 1启用 2冻结
     */
    private Integer status;
    /**
     * 流程状态： 10 新建 11 通过 12 打回
     */
    @TableField("work_status")
    private Integer workStatus;
    /**
     * 备注
     */
    private String remark;
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

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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
        ", classCode=" + classCode +
        ", studentCode=" + studentCode +
        ", type=" + type +
        ", userName=" + userName +
        ", target=" + target +
        ", status=" + status +
        ", workStatus=" + workStatus +
        ", remark=" + remark +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
