package com.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 短信发送队列表
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-12
 */
@TableName("sys_sms_sequence")
public class SmsSequence extends Model<SmsSequence> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    private Long id;
    /**
     * 接收号码
     */
    private String number;
    /**
     * 短信发送模板
     */
    private String template;
    /**
     * 信息内容
     */
    private String content;
    /**
     * 发送时间
     */
    @TableField("sent_date")
    private Date sentDate;
    /**
     * 发送状态： 0 未发送； 1 已发送
     */
    @TableField("sent_status")
    private Integer sentStatus;
    /**
     * 发送结果
     */
    @TableField("sent_result")
    private String sentResult;
    /**
     * 发送结果描述
     */
    @TableField("sent_result_message")
    private String sentResultMessage;
    /**
     * SMS平台请求流水
     */
    @TableField("out_request_id")
    private String outRequestId;
    /**
     * SMS平台发送回执流水
     */
    @TableField("out_response_id")
    private String outResponseId;
    /**
     * 入队时间
     */
    @TableField("create_date")
    private Date createDate;
    /**
     * 状态： 0 无效； 1 有效
     */
    private Integer status;
    /**
     * 系统业务流水(可无)
     */
    @TableField("buss_id")
    private String bussId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public Integer getSentStatus() {
        return sentStatus;
    }

    public void setSentStatus(Integer sentStatus) {
        this.sentStatus = sentStatus;
    }

    public String getSentResult() {
        return sentResult;
    }

    public void setSentResult(String sentResult) {
        this.sentResult = sentResult;
    }

    public String getSentResultMessage() {
        return sentResultMessage;
    }

    public void setSentResultMessage(String sentResultMessage) {
        this.sentResultMessage = sentResultMessage;
    }

    public String getOutRequestId() {
        return outRequestId;
    }

    public void setOutRequestId(String outRequestId) {
        this.outRequestId = outRequestId;
    }

    public String getOutResponseId() {
        return outResponseId;
    }

    public void setOutResponseId(String outResponseId) {
        this.outResponseId = outResponseId;
    }

    public String getBussId() {
        return bussId;
    }

    public void setBussId(String bussId) {
        this.bussId = bussId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
        return "SmsSequence{" +
        "id=" + id +
        ", number=" + number +
        ", template=" + template +
        ", content=" + content +
        ", sentDate=" + sentDate +
        ", sentStatus=" + sentStatus +
        ", sentResult=" + sentResult +
        ", sentResultMessage=" + sentResultMessage +
        ", outRequestId=" + outRequestId +
        ", outResponseId=" + outResponseId +
        ", createDate=" + createDate +
        ", status=" + status +
        "}";
    }

    public static enum SentStatusEnum{
        NoSent(0, "未发送"),
        SendOver (1, "已发送")
        ;

        public int code;
        public String text;

        SentStatusEnum(int code, String text){
            this.code = code;
            this.text = text;
        }
    }
}
