package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
@ApiModel(value = "OrderItem", description = "订单项")
public class OrderItem extends Model<OrderItem> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(name = "id", value = "标示", position = 0, hidden = true)
    private Long id;

    /**
     * 订单号
     */
    @TableField("order_no")
    @ApiModelProperty(name = "orderNo", value = "订单号", position = 0, example="J23i181116221454490")
    private String orderNo;

    @TableField("course_cart_code")
    @ApiModelProperty(name = "courseCartCode", value = "选课单编码", position = 0, example="CC181225000001")
    private String courseCartCode;
    /**
     * 订单项目编码： DI + yyMMdd + 8位序列号
     */
    @TableField("item_code")
    @ApiModelProperty(name = "itemCode", value = "订单项目编码： DI + yyMMdd + 8位序列号", position = 0, example="DI18111600000001")
    private String itemCode;
    /**
     * 项目主体： 1 课程； 2  优惠券  3  积分
     */
    @TableField("item_object")
    @ApiModelProperty(name = "itemObject", value = "项目主体: 1 课程； 2  优惠券  3  积分", position = 0, example="1")
    private Integer itemObject;
    /**
     * 项目主体编码： 当项目主体为1课程时，主体编码为所购课程的编码；当项目主体为2 或 3 时，该列值为使用优惠券或积分的项目编码
     */
    @TableField("item_object_code")
    @ApiModelProperty(name = "itemObjectCode", value = "项目主体编码： 当项目主体为1课程时，主体编码为所购课程的编码；当项目主体为2 或 3 时，该列值为使用优惠券或积分的项目编码", position = 0, example="BJ000001")
    private String itemObjectCode;
    /**
     * 订单项目金额
     */
    @TableField("item_amount")
    @ApiModelProperty(name = "itemAmount", value = "订单项目金额， 单位： 分", position = 0, example="10000")
    private Long itemAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseCartCode() {
        return courseCartCode;
    }

    public void setCourseCartCode(String courseCartCode) {
        this.courseCartCode = courseCartCode;
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
