package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 试题库
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-30
 */
@TableName("tb_question")
public class Question extends Model<Question> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 试题编码：ST + 8位序列码
     */
    private String code;
    /**
     * 试题题目： 题目以图片的方式存放在附件表
     */
    private String question;
    /**
     * 试题类型： 1 单选题； 2 多选题； 3 填空题； 4 主观题
     */
    private Integer type;
    /**
     * 试题学科：10数学 11语文 13化学
     */
    private Integer subject;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 参考答案
     */
    @TableField("expact_answer")
    private String expactAnswer;


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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExpactAnswer() {
        return expactAnswer;
    }

    public void setExpactAnswer(String expactAnswer) {
        this.expactAnswer = expactAnswer;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Question{" +
        "id=" + id +
        ", code=" + code +
        ", question=" + question +
        ", type=" + type +
        ", subject=" + subject +
        ", status=" + status +
        ", expactAnswer=" + expactAnswer +
        "}";
    }
}
