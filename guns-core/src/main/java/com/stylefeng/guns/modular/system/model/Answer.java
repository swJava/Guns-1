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
 * 答卷
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-30
 */
@TableName("tb_answer")
public class Answer extends Model<Answer> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 试卷编码： SJ + 6位序列码
     */
    private String code;
    /**
     * 答题学员
     */
    @TableField("student_code")
    private String studentCode;
    /**
     * 开始时间
     */
    @TableField("begin_date")
    private Date beginDate;
    /**
     * 结束时间
     */
    @TableField("end_date")
    private Date endDate;
    /**
     * 耗时时间， 单位： 分钟
     */
    private Integer duration;
    /**
     * 总分
     */
    private Integer score;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态， 0 未答题   1 已答题
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

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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
        return "Answer{" +
        "id=" + id +
        ", code=" + code +
        ", studentCode=" + studentCode +
        ", beginDate=" + beginDate +
        ", endDate=" + endDate +
        ", duration=" + duration +
        ", score=" + score +
        ", remark=" + remark +
        ", status=" + status +
        "}";
    }
}
