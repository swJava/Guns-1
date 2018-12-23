package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 试题权重
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-22
 */
@TableName("tb_question_item")
@ApiModel(value = "QuestionItem", description = "试题答案项目")
public class QuestionItem extends Model<QuestionItem> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;
    /**
     * 试题编码
     */
    @ApiModelProperty(value = "questionCode", name = "试题编码", example = "ST00000001")
    @TableField("question_code")
    private String questionCode;
    /**
     * 题目选项
     */
    @ApiModelProperty(value = "text", name = "答案项描述", example = "x = 1")
    private String text;
    /**
     * 选项值
     */
    @ApiModelProperty(value = "value", name = "答案项值（便于自动计算的值）", example = "1")
    private String value;
    /**
     * 状态： 0 未启用  1 启用
     */
    @ApiModelProperty(hidden = true)
    private String status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "QuestionItem{" +
        "id=" + id +
        ", questionCode=" + questionCode +
        ", text=" + text +
        ", value=" + value +
        ", status=" + status +
        "}";
    }
}
