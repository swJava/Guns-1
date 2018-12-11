package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 答卷明细
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-30
 */
@TableName("tb_answer_detail")
public class AnswerDetail extends Model<AnswerDetail> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("paper_code")
    private String paperCode;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AnswerDetail{" +
        "id=" + id +
        ", paperCode=" + paperCode +
        ", studentAnswer=" + studentAnswer +
        ", answer=" + answer +
        ", status=" + status +
        "}";
    }
}
