package com.stylefeng.guns.modular.orderMGR.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.orderMGR.service.IOrderService;
import com.stylefeng.guns.modular.orderMGR.warpper.OrderWrapper;
import com.stylefeng.guns.modular.system.model.Order;
import com.stylefeng.guns.modular.system.model.Student;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 订单管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-18 22:46:16
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    private String PREFIX = "/orderMGR/order/";

    @Autowired
    private IOrderService orderService;

    /**
     * 跳转到订单管理首页
     */
    @RequestMapping("/class")
    public String index() {
        return PREFIX + "order.html";
    }

    /**
     * 跳转到修改订单管理
     */
    @RequestMapping("/class/order_update/{orderId}")
    public String orderUpdate(@PathVariable Integer orderId, Model model) {
        Order order = orderService.selectById(orderId);
        model.addAttribute("item", order);
        LogObjectHolder.me().set(order);
        return PREFIX + "order_edit.html";
    }

    /**
     * 获取订单管理列表
     */
    @RequestMapping(value = "/class/list")
    @ResponseBody
    public Object list(String condition) {
        //分页查詢
        Page<Student> page = new PageFactory<Student>().defaultPage();
        Page<Map<String, Object>> pageMap = orderService.selectMapsPage(page, new EntityWrapper<Order>() {
            {
                //name条件分页
                if (StringUtils.isNotEmpty(condition)) {
                    like("acceptNo", condition);
                }
            }
        });
        //包装数据
        new OrderWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }

    /**
     * 删除订单管理
     */
    @RequestMapping(value = "/class/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer orderId) {
        orderService.deleteById(orderId);
        return SUCCESS_TIP;
    }

    /**
     * 修改订单管理
     */
    @RequestMapping(value = "/class/update")
    @ResponseBody
    public Object update(Order order) {
        orderService.updateById(order);
        return SUCCESS_TIP;
    }

    /**
     * 订单管理详情
     */
    @RequestMapping(value = "/class/detail/{orderId}")
    @ResponseBody
    public Object detail(@PathVariable("orderId") Integer orderId) {
        return orderService.selectById(orderId);
    }
}
