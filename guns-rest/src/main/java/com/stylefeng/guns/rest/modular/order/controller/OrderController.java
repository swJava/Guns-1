package com.stylefeng.guns.rest.modular.order.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.classMGR.service.IClassService;
import com.stylefeng.guns.modular.memberMGR.service.IMemberService;
import com.stylefeng.guns.modular.orderMGR.OrderServiceTypeEnum;
import com.stylefeng.guns.modular.orderMGR.service.ICourseCartService;
import com.stylefeng.guns.modular.orderMGR.service.IOrderService;
import com.stylefeng.guns.modular.studentMGR.service.IStudentService;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import com.stylefeng.guns.rest.modular.order.requester.OrderPostRequester;
import com.stylefeng.guns.rest.modular.order.responser.CartListResponser;
import com.stylefeng.guns.rest.modular.order.responser.OrderListResponser;
import com.stylefeng.guns.rest.modular.order.responser.OrderPostResponser;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单
 * Created by 罗华.
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单接口")
@Validated
public class OrderController {

    @Autowired
    private ICourseCartService courseCartService;

    @Autowired
    private IMemberService memberService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IClassService classService;

    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/cart/join", method = RequestMethod.POST)
    @ApiOperation(value="加入选课单", httpMethod = "POST", response = SimpleResponser.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", example = "18580255110"),
            @ApiImplicitParam(name = "student", value = "学员编码", required = true, dataType = "String", example = "XY000001"),
            @ApiImplicitParam(name = "classCode", value = "班级编码", required = true, dataType = "String", example = "BJ000001")
    }
    )
    public Responser joinCart(
            @NotBlank(message = "用户名不能为空")
            String userName,
            @NotBlank(message = "学员不能为空")
            String student,
            @NotBlank(message = "班级不能为空")
            String classCode){

        Member member = memberService.get(userName);

        if (null == member)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        Student existStudent = studentService.get(student);

        if (null == student)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        com.stylefeng.guns.modular.system.model.Class classInfo = classService.get(classCode);

        if (null == classInfo)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        courseCartService.join(member, existStudent, classInfo);

        return SimpleResponser.success();
    }

    @RequestMapping(value = "/cart/remove", method = RequestMethod.POST)
    @ApiOperation(value="从选课单移除", httpMethod = "POST", response = SimpleResponser.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", example = "18580255110"),
            @ApiImplicitParam(name = "student", value = "学员编码", required = true, dataType = "String", example = "XY000001"),
            @ApiImplicitParam(name = "classCode", value = "班级编码", required = true, dataType = "String", example = "BJ000001")
    }
    )
    public Responser removeCart(
            @NotBlank(message = "用户名不能为空")
            String userName,
            @NotBlank(message = "学员不能为空")
            String student,
            @NotBlank(message = "班级不能为空")
            String classCode){

        Member member = memberService.get(userName);

        if (null == member)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        Student existStudent = studentService.get(student);

        if (null == student)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        com.stylefeng.guns.modular.system.model.Class classInfo = classService.get(classCode);

        if (null == classInfo)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        courseCartService.remove(member, existStudent, classInfo);

        return SimpleResponser.success();
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation(value="生成订单", httpMethod = "POST", response = OrderPostResponser.class)
    public Responser createOrder(
            @Valid
            @RequestBody
            @ApiParam(required = true, value = "订单提交信息")
            OrderPostRequester requester){

        Member member = memberService.get(requester.getMember());

        if (null == member)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        PayMethodEnum payMethod = PayMethodEnum.instanceOf(requester.getPayMethod());
        if (null == payMethod || PayMethodEnum.NULL.equals(payMethod))
            throw new ServiceException(MessageConstant.MessageCode.PAY_METHOD_NOT_FOUND);

        OrderServiceTypeEnum serviceType = OrderServiceTypeEnum.instanceOf(requester.getService());

        Map<String, Object> extraPostData = new HashMap<String, Object>();

        OrderPostResponser responser = null;
        switch (serviceType){
            case Order:
                // 报名(订购)
                String orderNo = orderService.order(member, requester.getAddList(), payMethod, extraPostData);
                responser = OrderPostResponser.me(orderNo);
                break;
        }

        if (null == responser)
            throw new ServiceException(MessageConstant.MessageCode.SYS_EXCEPTION);

        return responser;
    }

    @ApiOperation(value="订单变更", httpMethod = "POST")
    @RequestMapping("/change")
    public Responser changeOrder(OrderPostRequester requester){
        return null;
    }

    @ApiOperation(value="选课单", httpMethod = "POST", response = CartListResponser.class)
    @RequestMapping("/cart/list")
    public Responser listCart(
            @NotBlank(message = "用户名不能为空")
            String userName,
            Integer status
    ){
        Wrapper<CourseCart> queryWrapper = new EntityWrapper<CourseCart>();
        queryWrapper.eq("user_name", userName);

        if (null != status)
            queryWrapper.eq("status", status);

        List<CourseCart> courseCartList = courseCartService.selectList(queryWrapper);

        return CartListResponser.me(courseCartList);
    }

    @ApiOperation(value="订单列表", httpMethod = "POST", response = OrderListResponser.class)
    @RequestMapping("/order/list")
    public Responser listOrder(
            @NotBlank(message = "用户名不能为空")
            String userName,
            Integer status
    ){
        Wrapper<Order> queryWrapper = new EntityWrapper<Order>();
        queryWrapper.eq("user_name", userName);

        if (null != status)
            queryWrapper.eq("status", status);

        List<Order> orderList = orderService.selectList(queryWrapper);

        return OrderListResponser.me(orderList);

    }


}
