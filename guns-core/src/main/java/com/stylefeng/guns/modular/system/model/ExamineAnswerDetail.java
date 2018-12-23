package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 学生答卷明细
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-22
 */
@TableName("tb_examine_answer_detail")
public class ExamineAnswerDetail extends Model<ExamineAnswerDetail> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 答卷编码
     */
    @TableField("answer_code")
    private String answerCode;
    /**
     * 题目编码
     */
    @TableField("question_code")
    private String questionCode;
    /**
     * 学生答案
     */
    @TableField("student_answer")
    private String studentAnswer;
    /**
     * 标准答案
     */
    private String answer;
    /**
     * 状态:1=对，2=错
     */
    private Integer status;
    /**
     * 获得分值
     */
    private Integer score;
    /**
     * 总分值
     */
    @TableField("total_score")
    private Integer totalScore;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswerCode() {
        return answerCode;
    }

    public void setAnswerCode(String answerCode) {
        this.answerCode = answerCode;
    }

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ExamineAnswerDetail{" +
        "id=" + id +
        ", answerCode=" + answerCode +
        ", questionCode=" + questionCode +
        ", studentAnswer=" + studentAnswer +
        ", answer=" + answer +
        ", status=" + status +
        ", score=" + score +
        ", totalScore=" + totalScore +
        "}";
    }
}
