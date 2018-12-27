package com.stylefeng.guns.modular.payMGR.service;

import com.stylefeng.guns.modular.system.model.Order;
import com.stylefeng.guns.modular.system.model.OrderItem;

import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/27 14:28
 * @Version 1.0
 */
public interface IPayService {
    /**
     * 生成支付订单
     *
     * @param order
     * @param itemList
     * @return 支付流水
     */
    String createPayOrder(Order order);
}
