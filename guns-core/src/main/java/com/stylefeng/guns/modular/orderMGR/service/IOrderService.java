package com.stylefeng.guns.modular.orderMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.orderMGR.OrderAddList;
import com.stylefeng.guns.modular.system.model.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author simple.song
 * @since 2018-10-18
 */
public interface IOrderService extends IService<Order> {
    /**
     * 生成订单
     *
     * @param member
     * @param addList
     * @param payMethod
     * @param extraPostData
     * @return
     */
    Order order(Member member, OrderAddList addList, PayMethodEnum payMethod, Map<String, Object> extraPostData);

    /**
     * 订单项目列表
     *
     * @param acceptNo
     * @param course
     * @return
     */
    List<OrderItem> listItems(String acceptNo, OrderItemTypeEnum type);

    /**
     * 获取订单
     *
     * @param orderNo
     * @return
     */
    Order get(String orderNo);

    /**
     *
     * @param order
     * @param paySequence
     */
    void completePay(Order order, String paySequence);
}
