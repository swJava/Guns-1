package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 试卷项
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-22
 */
@TableName("tb_examine_paper_item")
public class ExaminePaperItem extends Model<ExaminePaperItem> {

    private static final long serialVersionUID = 1L;

    /**
     * 序列
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 试卷编码
     */
    @TableField("paper_code")
    private String paperCode;
    /**
     * 题目编码
     */
    @TableField("question_code")
    private String questionCode;
    /**
     * 分值
     */
    private String score;

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

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
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
        return "ExaminePaperItem{" +
        "id=" + id +
        ", paperCode=" + paperCode +
        ", questionCode=" + questionCode +
        ", score=" + score +
        ", status=" + status +
        "}";
    }
}
