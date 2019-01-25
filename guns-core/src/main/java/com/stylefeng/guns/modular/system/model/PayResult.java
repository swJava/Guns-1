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
 * 支付结果
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-27
 */
@TableName("tb_pay_result")
public class PayResult extends Model<PayResult> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 应用标示
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
    @TableField("device_id")
    private String deviceId;
    /**
     * 商户订单号
     */
    @TableField("order_no")
    private String orderNo;
    /**
     * 支付流水
     */
    @TableField("out_sequence")
    private String outSequence;
    /**
     * 微信支付订单号
     */
    @TableField("out_order_no")
    private String outOrderNo;
    /**
     * 业务结果
     */
    @TableField("buss_result")
    private String bussResult;
    /**
     * 错误代码
     */
    @TableField("err_code")
    private String errCode;
    /**
     * 错误描述
     */
    @TableField("err_code_desp")
    private String errCodeDesp;
    /**
     * 用户标识
     */
    private String openid;
    /**
     * 付款银行
     */
    @TableField("bank_type")
    private String bankType;
    /**
     * 总金额， 单位： 分
     */
    @TableField("total_fee")
    private Integer totalFee;
    /**
     * 货币种类
     */
    @TableField("fee_type")
    private String feeType;
    /**
     * 现金支付金额，单位： 分
     */
    @TableField("cash_fee")
    private Integer cashFee;
    /**
     * 现金支付货币类型
     */
    @TableField("cash_fee_type")
    private String cashFeeType;
    /**
     * 代金券金额
     */
    @TableField("coupon_fee")
    private Integer couponFee;
    /**
     * 代金券使用数量
     */
    @TableField("coupon_count")
    private Integer couponCount;
    /**
     * 代金券ID， 多个代金券 使用英文，分隔
     */
    @TableField("coupon_ids")
    private String couponIds;
    /**
     * 单个代金券支付金额， 如使用多个代金券，使用英文，分隔
     */
    @TableField("coupon_fees")
    private String couponFees;
    /**
     * 商家数据包
     */
    private String attach;
    /**
     * 支付完成时间
     */
    @TableField("time_end")
    private Date timeEnd;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOutSequence() {
        return outSequence;
    }

    public void setOutSequence(String outSequence) {
        this.outSequence = outSequence;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public String getBussResult() {
        return bussResult;
    }

    public void setBussResult(String bussResult) {
        this.bussResult = bussResult;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDesp() {
        return errCodeDesp;
    }

    public void setErrCodeDesp(String errCodeDesp) {
        this.errCodeDesp = errCodeDesp;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getCashFee() {
        return cashFee;
    }

    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;
    }

    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    public Integer getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(Integer couponFee) {
        this.couponFee = couponFee;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public String getCouponIds() {
        return couponIds;
    }

    public void setCouponIds(String couponIds) {
        this.couponIds = couponIds;
    }

    public String getCouponFees() {
        return couponFees;
    }

    public void setCouponFees(String couponFees) {
        this.couponFees = couponFees;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PayResult{" +
        "id=" + id +
        ", appid=" + appid +
        ", mchId=" + mchId +
        ", deviceId=" + deviceId +
        ", orderNo=" + orderNo +
        ", outSequence=" + outSequence +
        ", outOrderNo=" + outOrderNo +
        ", bussResult=" + bussResult +
        ", errCode=" + errCode +
        ", errCodeDesp=" + errCodeDesp +
        ", openid=" + openid +
        ", bankType=" + bankType +
        ", totalFee=" + totalFee +
        ", feeType=" + feeType +
        ", cashFee=" + cashFee +
        ", cashFeeType=" + cashFeeType +
        ", couponFee=" + couponFee +
        ", couponCount=" + couponCount +
        ", couponIds=" + couponIds +
        ", couponFees=" + couponFees +
        ", attach=" + attach +
        ", timeEnd=" + timeEnd +
        "}";
    }
}
