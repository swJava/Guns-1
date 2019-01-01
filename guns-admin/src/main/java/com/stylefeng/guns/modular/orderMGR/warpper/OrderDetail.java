package com.stylefeng.guns.modular.orderMGR.warpper;

import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.system.model.Order;
import org.springframework.beans.BeanUtils;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/1 8:48
 * @Version 1.0
 */
public class OrderDetail extends Order {

    public static OrderDetail me(Order order) {
        OrderDetail orderDetail = new OrderDetail();
        if (null == order)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"订单不存在"});

        BeanUtils.copyProperties(order, orderDetail);
        return orderDetail;
    }

    public OrderDetail warp() {
        return this;
    }
}
