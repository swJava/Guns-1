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
 * @author simple
 * @since 2018-11-15
 */
@TableName("tb_answer_paper")
public class AnswerPaper extends Model<AnswerPaper> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 试卷编码
     */
    @TableField("exam_code")
    private String examCode;
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
     * 总分
     */
    private Integer score;
    /**
     * 备注
     */
    private String remark;
    /**
     * 年级
     */
    private Integer grade;
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

    public String getExamCode() {
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
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
        return "AnswerPaper{" +
        "id=" + id +
        ", examCode=" + examCode +
        ", studentCode=" + studentCode +
        ", beginDate=" + beginDate +
        ", endDate=" + endDate +
        ", score=" + score +
        ", remark=" + remark +
        ", grade=" + grade +
        ", status=" + status +
        "}";
    }
}
