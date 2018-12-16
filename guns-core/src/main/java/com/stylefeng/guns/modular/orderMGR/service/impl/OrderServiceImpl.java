package com.stylefeng.guns.modular.orderMGR.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.orderMGR.OrderAddList;
import com.stylefeng.guns.modular.orderMGR.service.IOrderService;
import com.stylefeng.guns.modular.system.dao.OrderItemMapper;
import com.stylefeng.guns.modular.system.dao.OrderMapper;
import com.stylefeng.guns.modular.system.dao.OrderMemberMapper;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.util.CodeKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author simple.song
 * @since 2018-10-18
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderMemberMapper orderMemberMapper;

    @Override
    public String order(Member member, OrderAddList addList, PayMethodEnum payMethod, Map<String, Object> extraPostData) {

        List<OrderItem> itemList = buildOrderItem(addList, extraPostData);
        // 算费
        long amount = calculate(itemList);

        Order order = buildOrder(member, amount, payMethod, extraPostData);

        insert(order);

        String orderNo = order.getAcceptNo();

        for(OrderItem orderItem : itemList){
            orderItem.setOrderNo(orderNo);
            orderItemMapper.insert(orderItem);
        }

        OrderMember orderMember = new OrderMember();
        orderMember.setOrderNo(orderNo);
        orderMember.setUsername(member.getUserName());

        orderMemberMapper.insert(orderMember);
        return orderNo;
    }

    @Override
    public List<OrderItem> listItems(String acceptNo) {
        return null;
    }

    /**
     * 算费
     *
     * @param itemList
     * @return
     */
    private long calculate(List<OrderItem> itemList) {

        long amount = 0L;

        for(OrderItem orderItem : itemList){
            OrderItemTypeEnum type = OrderItemTypeEnum.instanceOf(orderItem.getItemObject());
            switch (type){
                case Course:
                    amount += calcCourse(orderItem);
                    break;
                default:
                    break;
            }
        }
        return amount;
    }

    /**
     * 课程算费
     *
     * @param orderItem
     * @return
     */
    private long calcCourse(OrderItem orderItem) {
        return orderItem.getItemAmount();
    }

    private List<OrderItem> buildOrderItem(OrderAddList addList, Map<String, Object> extraPostData) {
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        for(OrderItem orderItem : addList){
            orderItem.setItemCode(CodeKit.generateOrderItem());
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }

    private Order buildOrder(Member member, long amount, PayMethodEnum payMethod, Map<String, Object> extraPostData) {
        Order order = new Order();
        Date now = new Date();

        order.setAcceptNo(CodeKit.generateOrder());
        order.setAcceptDate(now);
        order.setAmount(amount);
        order.setPayStatus(PayStateEnum.NoPay.code);
        order.setPayMethod(payMethod.code);
        order.setUserName(member.getUserName());

        order.setStatus(OrderStateEnum.Valid.code);

        return order;
    }
}
