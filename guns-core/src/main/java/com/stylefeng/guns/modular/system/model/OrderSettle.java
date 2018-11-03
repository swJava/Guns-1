package com.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 订单结算
 * </p>
 *
 * @author simple
 * @since 2018-11-03
 */
@TableName("tb_order_settle")
public class OrderSettle extends Model<OrderSettle> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    private Long id;
    /**
     * 订单号
     */
    @TableField("order_no")
    private String orderNo;
    /**
     * 结算单编码
     */
    private String code;
    /**
     * 订单总金额
     */
    private Long amount;
    /**
     * 实际支付金额
     */
    @TableField("pay_amount")
    private Long payAmount;
    /**
     * 总使用券数
     */
    @TableField("coupon_count")
    private Integer couponCount;
    /**
     * 券抵扣金额
     */
    @TableField("coupon_amount")
    private Long couponAmount;
    /**
     * 总使用积分数
     */
    @TableField("use_score")
    private Integer useScore;
    /**
     * 积分抵扣金额
     */
    @TableField("score_amount")
    private Long scoreAmount;
    /**
     * 结算时间
     */
    @TableField("settle_date")
    private Date settleDate;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 用户账号
     */
    @TableField("user_name")
    private String userName;


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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public Long getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Long couponAmount) {
        this.couponAmount = couponAmount;
    }

    public Integer getUseScore() {
        return useScore;
    }

    public void setUseScore(Integer useScore) {
        this.useScore = useScore;
    }

    public Long getScoreAmount() {
        return scoreAmount;
    }

    public void setScoreAmount(Long scoreAmount) {
        this.scoreAmount = scoreAmount;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OrderSettle{" +
        "id=" + id +
        ", orderNo=" + orderNo +
        ", code=" + code +
        ", amount=" + amount +
        ", payAmount=" + payAmount +
        ", couponCount=" + couponCount +
        ", couponAmount=" + couponAmount +
        ", useScore=" + useScore +
        ", scoreAmount=" + scoreAmount +
        ", settleDate=" + settleDate +
        ", status=" + status +
        ", userName=" + userName +
        "}";
    }
}
