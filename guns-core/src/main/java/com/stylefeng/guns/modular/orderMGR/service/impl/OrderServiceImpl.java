package com.stylefeng.guns.modular.orderMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.education.service.IScheduleClassService;
import com.stylefeng.guns.modular.education.service.IScheduleStudentService;
import com.stylefeng.guns.modular.education.service.IStudentClassService;
import com.stylefeng.guns.modular.memberMGR.service.IMemberService;
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

    @Autowired
    private IScheduleClassService scheduleClassService;

    @Autowired
    private IScheduleStudentService scheduleStudentService;

    @Autowired
    private IStudentClassService studentClassService;

    @Autowired
    private IMemberService memberService;

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
                courseCartService.generateOrder(member.getUserName(), courseCart.getStudentCode(), orderItem.getItemObjectCode());
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
    public List<OrderItem> listItems(String orderNo, OrderItemTypeEnum type) {
        if (null == orderNo)
            return new ArrayList<OrderItem>();

        Wrapper<OrderItem> orderItemWrapper = new EntityWrapper<OrderItem>();
        orderItemWrapper.eq("order_no", orderNo);
        orderItemWrapper.eq("item_object", type.code);

        return orderItemMapper.selectList(orderItemWrapper);
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
    public void completePay(String order) {
        Order currOrder = get(order);

        currOrder.setPayStatus(PayStateEnum.PayOk.code);
        currOrder.setPayResult(PayStateEnum.PayOk.text);
        currOrder.setPayDate(new Date());

        updateById(currOrder);

        Wrapper<OrderItem> orderItemWrapper = new EntityWrapper<OrderItem>();
        orderItemWrapper.eq("order_no", order);
        orderItemWrapper.eq("item_object", OrderItemTypeEnum.Course.code);

        List<OrderItem> orderItemList = orderItemMapper.selectList(orderItemWrapper);

        for(OrderItem courseItem : orderItemList){

            CourseCart courseCart = courseCartService.get(courseItem.getCourseCartCode());

            Wrapper<ScheduleClass> scheduleClassWrapper = new EntityWrapper<ScheduleClass>();
            scheduleClassWrapper.eq("class_code", courseCart.getClassCode());

            List<ScheduleClass> classScheduleList = scheduleClassService.selectList(scheduleClassWrapper);

            // 学员报班信息表
            StudentClass studentClass = new StudentClass();
            studentClass.setStudentCode(courseCart.getStudentCode());
            studentClass.setClassCode(courseCart.getClassCode());
            studentClass.setClassName(courseCart.getClassName());
            studentClass.setPeriod(classScheduleList.size());
            studentClass.setStatus(GenericState.Valid.code);

            studentClassService.insert(studentClass);

            for(ScheduleClass classSchedule : classScheduleList){
                ScheduleStudent scheduleStudent = new ScheduleStudent();
                scheduleStudent.setCode(CodeKit.generateStudentSchedule());
                scheduleStudent.setStudentCode(courseCart.getStudentCode());
                scheduleStudent.setStudentName(courseCart.getStudent());
                scheduleStudent.setClassCode(classSchedule.getClassCode());
                scheduleStudent.setClassName(courseCart.getClassName());
                scheduleStudent.setOutlineCode(classSchedule.getOutlineCode());
                scheduleStudent.setOutline(classSchedule.getOutline());
                scheduleStudent.setStudyDate(classSchedule.getStudyDate());
                scheduleStudent.setStatus(GenericState.Valid.code);

                scheduleStudentService.insert(scheduleStudent);
            }
        }
    }

    @Override
    public Member getMemberInfo(String orderNo) {

        if (null == orderNo)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"订单号"});

        Order order = get(orderNo);

        if (null == order)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"订单"});

        OrderMember orderMemberWrapper = new OrderMember();
        orderMemberWrapper.setOrderNo(orderNo);

        OrderMember existOrderMember = orderMemberMapper.selectOne(orderMemberWrapper);

        if (null == existOrderMember)
            return null;

        return memberService.get(existOrderMember.getUsername());
    }

    @Override
    public void cancel(Member member, String orderNo) {
        if (null == orderNo)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"订单号"});

        Order order = get(orderNo);

        if (null == order)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"订单"});

        order.setStatus(OrderStateEnum.InValid.code);
        updateById(order);
    }

    @Override
    public void failedPay(String order, String message) {
        Order currOrder = get(order);

        currOrder.setPayStatus(PayStateEnum.Failed.code);
        currOrder.setPayResult(message);
        currOrder.setPayDate(new Date());

        updateById(currOrder);
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
        order.setDesc("订购课程");

        order.setStatus(OrderStateEnum.Valid.code);

        return order;
    }
}
