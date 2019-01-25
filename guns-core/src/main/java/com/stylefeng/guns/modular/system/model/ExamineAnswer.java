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
 * 试卷
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-22
 */
@TableName("tb_examine_answer")
public class ExamineAnswer extends Model<ExamineAnswer> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 答卷编码： DJ + 8位序列码
     */
    private String code;
    /**
     * 试卷编码： SJ =８位序列码
     */
    @TableField("paper_code")
    private String paperCode;
    /**
     * 学员编码
     */
    @TableField("student_code")
    private String studentCode;
    /**
     * 题目数量
     */
    private Integer quota;
    /**
     * 总分值
     */
    @TableField("total_score")
    private Integer totalScore;
    /**
     * 考试时间
     */
    @TableField("exam_time")
    private Integer examTime;
    /**
     * 已答题数量
     */
    @TableField("answer_quota")
    private Integer answerQuota;
    /**
     * 最后答题编码
     */
    @TableField("last_answer_question")
    private String lastAnswerQuestion;
    /**
     * 上次答题时间
     */
    @TableField("last_answer_date")
    private Date lastAnswerDate;
    /**
     * 状态:0=新建，1=正在答题， 2=暂停答题， 3=已交卷， 4=完成批改
     */
    private Integer status;
    /**
     * 试卷创建时间
     */
    @TableField("create_date")
    private Date createDate;
    /**
     * 得分数
     */
    private Integer score;
    /**
     * 测试开始时间
     */
    @TableField("begin_date")
    private Date beginDate;
    /**
     * 测试结束时间
     */
    @TableField("end_date")
    private Date endDate;
    /**
     * 实际耗时时间
     */
    private Integer duration;
    /**
     * 评卷人
     */
    private String teacher;


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

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getExamTime() {
        return examTime;
    }

    public void setExamTime(Integer examTime) {
        this.examTime = examTime;
    }

    public Integer getAnswerQuota() {
        return answerQuota;
    }

    public void setAnswerQuota(Integer answerQuota) {
        this.answerQuota = answerQuota;
    }

    public String getLastAnswerQuestion() {
        return lastAnswerQuestion;
    }

    public void setLastAnswerQuestion(String lastAnswerQuestion) {
        this.lastAnswerQuestion = lastAnswerQuestion;
    }

    public Date getLastAnswerDate() {
        return lastAnswerDate;
    }

    public void setLastAnswerDate(Date lastAnswerDate) {
        this.lastAnswerDate = lastAnswerDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ExamineAnswer{" +
        "id=" + id +
        ", code=" + code +
        ", paperCode=" + paperCode +
        ", studentCode=" + studentCode +
        ", quota=" + quota +
        ", totalScore=" + totalScore +
        ", examTime=" + examTime +
        ", answerQuota=" + answerQuota +
        ", lastAnswerQuestion=" + lastAnswerQuestion +
        ", lastAnswerDate=" + lastAnswerDate +
        ", status=" + status +
        ", createDate=" + createDate +
        ", score=" + score +
        ", beginDate=" + beginDate +
        ", endDate=" + endDate +
        ", duration=" + duration +
        ", teacher=" + teacher +
        "}";
    }
}
