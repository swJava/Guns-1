package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
@ApiModel(value = "Order", description = "订单")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;
    /**
     * 订单号
     */
    @TableField("accept_no")
    @ApiModelProperty(name = "acceptNo", value = "订单号", example = "06H81901011513480077")
    private String acceptNo;
    /**
     * 生成时间
     */
    @TableField("accept_date")
    @ApiModelProperty(name = "acceptDate", value = "生成时间", example = "2018-01-10 12:00:01")
    private Date acceptDate;
    /**
     * 状态
     */
    @ApiModelProperty(hidden = true)
    private Integer status;
    /**
     * 金额
     */
    @ApiModelProperty(name = "amount", value = "金额， 单位： 分", example = "100")
    private Long amount;
    /**
     * 支付状态: -1=支付失败 0=待支付 1=用户已支付 2=支付成功 3=超时
     */
    @TableField("pay_status")
    @ApiModelProperty(hidden = true)
    private Integer payStatus;
    /**
     * 支付结果
     */
    @TableField("pay_result")
    @ApiModelProperty(hidden = true)
    private String payResult;
    /**
     * 支付时间
     */
    @TableField("pay_date")
    @ApiModelProperty(name = "payDate", value = "支付时间", example = "2018-01-10 12:00:01")
    private Date payDate;
    /**
     * 支付渠道：21 银联 22 微信
     */
    @TableField("pay_method")
    @ApiModelProperty(hidden = true)
    private Integer payMethod;
    /**
     * 用户账号
     */
    @TableField("user_name")
    @ApiModelProperty(name = "userName", value = "用户名", example = "huahua")
    private String userName;
    /**
     * 支付订单号
     */
    @TableField("out_order_no")
    @ApiModelProperty(name = "outOrderNo", value = "支付订单号", example = "wx271013515312207169df757e1056554739")
    private String outOrderNo;
    /**
     * 支付流水
     */
    @TableField("out_sequence")
    @ApiModelProperty(hidden = true)
    private String outSequence;
    /**
     * 订单描述
     */
    @ApiModelProperty(hidden = true)
    private String desc;


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

    public String getPayResult() {
        return payResult;
    }

    public void setPayResult(String payResult) {
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

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public String getOutSequence() {
        return outSequence;
    }

    public void setOutSequence(String outSequence) {
        this.outSequence = outSequence;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
        ", outOrderNo=" + outOrderNo +
        ", outSequence=" + outSequence +
        "}";
    }
}
