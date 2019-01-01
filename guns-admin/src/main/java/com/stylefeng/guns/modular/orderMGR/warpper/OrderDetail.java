package com.stylefeng.guns.modular.orderMGR.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.system.model.Order;
import com.stylefeng.guns.modular.system.model.PayStateEnum;
import org.springframework.beans.BeanUtils;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/1 8:48
 * @Version 1.0
 */
public class OrderDetail extends Order {

    private String payStateDesp;

    private String payChannelDesp;

    public static OrderDetail me(Order order) {
        OrderDetail orderDetail = new OrderDetail();
        if (null == order)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"订单不存在"});

        BeanUtils.copyProperties(order, orderDetail);

        PayStateEnum payStateEnum = PayStateEnum.instanceOf(orderDetail.getPayStatus());
        if (null == payStateEnum){
            orderDetail.setPayStateDesp("未知");
        }else {
            orderDetail.setPayStateDesp(payStateEnum.text);
        }

        orderDetail.setPayChannelDesp(ConstantFactory.me().getPayMethodName(orderDetail.getPayMethod()));

        return orderDetail;
    }

    public String getPayStateDesp() {
        return payStateDesp;
    }

    public void setPayStateDesp(String payStateDesp) {
        this.payStateDesp = payStateDesp;
    }

    public String getPayChannelDesp() {
        return payChannelDesp;
    }

    public void setPayChannelDesp(String payChannelDesp) {
        this.payChannelDesp = payChannelDesp;
    }

    public OrderDetail warp() {
        return this;
    }
}
