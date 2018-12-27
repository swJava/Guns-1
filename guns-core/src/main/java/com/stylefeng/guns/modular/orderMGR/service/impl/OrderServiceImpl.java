package com.stylefeng.guns.modular.orderMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.orderMGR.OrderAddList;
import com.stylefeng.guns.modular.orderMGR.service.ICourseCartService;
import com.stylefeng.guns.modular.orderMGR.service.IOrderService;
import com.stylefeng.guns.modular.payMGR.service.IPayService;
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

    @Autowired
    private ICourseCartService courseCartService;

    @Autowired
    private IPayService payService;

    @Override
    public Order order(Member member, OrderAddList addList, PayMethodEnum payMethod, Map<String, Object> extraPostData) {

        List<OrderItem> itemList = buildOrderItem(addList, extraPostData);
        // 算费
        long amount = calculate(itemList);
        Order order = buildOrder(member, amount, payMethod, extraPostData);
        // 生成订单
        insert(order);
        // 生成订单项
        String orderNo = order.getAcceptNo();
        for(OrderItem orderItem : itemList){
            orderItem.setOrderNo(orderNo);
            orderItemMapper.insert(orderItem);

            CourseCart courseCart = courseCartService.get(orderItem.getCourseCartCode());
            if (null != courseCart && OrderItemTypeEnum.Course.equals(OrderItemTypeEnum.instanceOf(orderItem.getItemObject()))){
                // 清理购物车信息
                courseCartService.generateOrder(member.getUserName(), courseCart.getStudent(), orderItem.getItemObjectCode());
            }
        }

        // 生成订单用户信息
        OrderMember orderMember = new OrderMember();
        orderMember.setOrderNo(orderNo);
        orderMember.setUsername(member.getUserName());

        orderMemberMapper.insert(orderMember);
        return order;
    }

    @Override
    public List<OrderItem> listItems(String acceptNo, OrderItemTypeEnum course) {
        return null;
    }

    @Override
    public Order get(String orderNo) {
        if (null == orderNo)
            return null;

        Wrapper<Order> queryWrapper = new EntityWrapper<Order>();
        queryWrapper.eq("accept_no", orderNo);
        return selectOne(queryWrapper);
    }

    @Override
    public void completePay(Order order, String paySequence) {

        order.setOutSequence(paySequence);

        order.setPayStatus(PayStateEnum.Paying.code);

        updateById(order);
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
            OrderItemTypeEnum type = OrderItemTypeEnum.instanceOf(orderItem.getItemObject());

            if (OrderItemTypeEnum.Course.equals(type)){
                // 是课程， 但是没有选课单号
                String courseCartCode = orderItem.getCourseCartCode();
                if (null == courseCartCode)
                    throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"选课单号"});

                CourseCart courseCart = courseCartService.get(courseCartCode);
                if (null == courseCart)
                    throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"选课单"} );

                // 是课程，判断是否已下订单
                Wrapper<OrderItem> queryWrapper = new EntityWrapper<OrderItem>();
                queryWrapper.eq("course_cart_code", orderItem.getCourseCartCode());

                if (orderItemMapper.selectCount(queryWrapper) > 0)
                    // 不能重复提交
                    throw new ServiceException(MessageConstant.MessageCode.ORDER_REQUEST_ORDERED);
            }
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
