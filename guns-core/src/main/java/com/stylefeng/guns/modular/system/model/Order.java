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
 * 订单
 * </p>
 *
 * @author simple
 * @since 2018-11-03
 */
@TableName("tb_order")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 订单号
     */
    @TableField("accept_no")
    private String acceptNo;
    /**
     * 生成时间
     */
    @TableField("accept_date")
    private Date acceptDate;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 金额
     */
    private Long amount;
    /**
     * 支付状态:1=待支付 2=已支付 3=超时
     */
    @TableField("pay_status")
    private Integer payStatus;
    /**
     * 支付结果：11成功 12失败 
     */
    @TableField("pay_result")
    private Integer payResult;
    /**
     * 支付时间
     */
    @TableField("pay_date")
    private Date payDate;
    /**
     * 支付渠道：21 银联 22 微信
     */
    @TableField("pay_method")
    private Integer payMethod;
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

    public String getAcceptNo() {
        return acceptNo;
    }

    public void setAcceptNo(String acceptNo) {
        this.acceptNo = acceptNo;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getPayResult() {
        return payResult;
    }

    public void setPayResult(Integer payResult) {
        this.payResult = payResult;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
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
        return "Order{" +
        "id=" + id +
        ", acceptNo=" + acceptNo +
        ", acceptDate=" + acceptDate +
        ", status=" + status +
        ", amount=" + amount +
        ", payStatus=" + payStatus +
        ", payResult=" + payResult +
        ", payDate=" + payDate +
        ", payMethod=" + payMethod +
        ", userName=" + userName +
        "}";
    }
}
