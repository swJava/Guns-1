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
 * 支付请求
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-27
 */
@TableName("tb_pay_request")
public class PayRequest extends Model<PayRequest> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 订单号
     */
    @TableField("order_no")
    private String orderNo;
    /**
     * 支付平台： 21 银联   22  微信
     */
    private Integer platform;
    /**
     * 支付流水，由支付平台返回
     */
    @TableField("out_sequence")
    private String outSequence;
    /**
     * 支付请求发起时间
     */
    @TableField("req_date")
    private Date reqDate;
    /**
     * 超时时间
     */
    @TableField("expire_date")
    private Date expireDate;
    /**
     * 支付请求签名
     */
    @TableField("req_sign")
    private String reqSign;
    /**
     * 请求结果
     */
    @TableField("out_req_result")
    private String outReqResult;
    /**
     * 请求结果签名值
     */
    @TableField("out_req_result_sign")
    private String outReqResultSign;
    /**
     * 外部系统返回码
     */
    @TableField("out_req_result_code")
    private String outReqResultCode;
    /**
     * 外部系统返回信息
     */
    @TableField("out_req_result_msg")
    private String outReqResultMsg;
    /**
     * 支付结果
     */
    @TableField("out_pay_result")
    private String outPayResult;
    /**
     * 支付结果通知签名值
     */
    @TableField("out_pay_result_sign")
    private String outPayResultSign;
    /**
     * 支付完成时间
     */
    @TableField("out_pay_date")
    private Date outPayDate;
    /**
     * 支付系统订单号
     */
    @TableField("out_order_no")
    private String outOrderNo;
    /**
     * 状态： -1 支付失败  0 待支付 1 支付成功 2 已关闭   3 已退款
     */
    private Integer status;
    /**
     * 应用ID
     */
    private String appid;
    /**
     * 商户号
     */
    @TableField("mch_id")
    private String mchId;
    /**
     * 设备号
     */
    @TableField("device_info")
    private String deviceInfo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getOutSequence() {
        return outSequence;
    }

    public void setOutSequence(String outSequence) {
        this.outSequence = outSequence;
    }

    public Date getReqDate() {
        return reqDate;
    }

    public void setReqDate(Date reqDate) {
        this.reqDate = reqDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getReqSign() {
        return reqSign;
    }

    public void setReqSign(String reqSign) {
        this.reqSign = reqSign;
    }

    public String getOutReqResult() {
        return outReqResult;
    }

    public void setOutReqResult(String outReqResult) {
        this.outReqResult = outReqResult;
    }

    public String getOutReqResultSign() {
        return outReqResultSign;
    }

    public void setOutReqResultSign(String outReqResultSign) {
        this.outReqResultSign = outReqResultSign;
    }

    public String getOutReqResultCode() {
        return outReqResultCode;
    }

    public void setOutReqResultCode(String outReqResultCode) {
        this.outReqResultCode = outReqResultCode;
    }

    public String getOutReqResultMsg() {
        return outReqResultMsg;
    }

    public void setOutReqResultMsg(String outReqResultMsg) {
        this.outReqResultMsg = outReqResultMsg;
    }

    public String getOutPayResult() {
        return outPayResult;
    }

    public void setOutPayResult(String outPayResult) {
        this.outPayResult = outPayResult;
    }

    public String getOutPayResultSign() {
        return outPayResultSign;
    }

    public void setOutPayResultSign(String outPayResultSign) {
        this.outPayResultSign = outPayResultSign;
    }

    public Date getOutPayDate() {
        return outPayDate;
    }

    public void setOutPayDate(Date outPayDate) {
        this.outPayDate = outPayDate;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PayRequest{" +
        "id=" + id +
        ", orderNo=" + orderNo +
        ", platform=" + platform +
        ", outSequence=" + outSequence +
        ", reqDate=" + reqDate +
        ", expireDate=" + expireDate +
        ", reqSign=" + reqSign +
        ", outReqResult=" + outReqResult +
        ", outReqResultSign=" + outReqResultSign +
        ", outReqResultCode=" + outReqResultCode +
        ", outReqResultMsg=" + outReqResultMsg +
        ", outPayResult=" + outPayResult +
        ", outPayResultSign=" + outPayResultSign +
        ", outPayDate=" + outPayDate +
        ", outOrderNo=" + outOrderNo +
        ", status=" + status +
        ", appid=" + appid +
        ", mchId=" + mchId +
        ", deviceInfo=" + deviceInfo +
        "}";
    }
}
