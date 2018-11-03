package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 订单结算项
 * </p>
 *
 * @author simple
 * @since 2018-11-03
 */
@TableName("tb_order_item")
public class OrderItem extends Model<OrderItem> {

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
     * 订单项目编码： DI + 班级后6位 + 8位序列号
     */
    @TableField("item_code")
    private String itemCode;
    /**
     * 项目主体： 1 课程； 2  优惠券  3  积分
     */
    @TableField("item_object")
    private Integer itemObject;
    /**
     * 项目主体编码： 当项目主体为1课程时，主体编码为所购课程的编码；当项目主体为2 或 3 时，该列值为使用优惠券或积分的项目编码
     */
    @TableField("item_object_code")
    private String itemObjectCode;
    /**
     * 订单项目金额
     */
    @TableField("item_amount")
    private Long itemAmount;


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

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getItemObject() {
        return itemObject;
    }

    public void setItemObject(Integer itemObject) {
        this.itemObject = itemObject;
    }

    public String getItemObjectCode() {
        return itemObjectCode;
    }

    public void setItemObjectCode(String itemObjectCode) {
        this.itemObjectCode = itemObjectCode;
    }

    public Long getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Long itemAmount) {
        this.itemAmount = itemAmount;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
        "id=" + id +
        ", orderNo=" + orderNo +
        ", itemCode=" + itemCode +
        ", itemObject=" + itemObject +
        ", itemObjectCode=" + itemObjectCode +
        ", itemAmount=" + itemAmount +
        "}";
    }
}
