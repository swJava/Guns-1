package com.stylefeng.guns.rest.modular.pay.controller;

import com.stylefeng.guns.modular.orderMGR.service.IOrderService;
import com.stylefeng.guns.modular.system.model.Order;
import com.stylefeng.guns.modular.system.model.PayResult;
import com.stylefeng.guns.rest.core.ApiController;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 罗华.
 */
@RestController
@RequestMapping("/pay")
@Api(tags = "支付接口")
public class PayController extends ApiController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/retry")
    public Responser payRetry(

    ){
        return null;
    }

    public Responser payWeixin(){
        return null;
    }

    public Responser payBank(){
        return null;
    }

    @RequestMapping(value = "/wx/notify", method = RequestMethod.POST)
    public Responser payNotifyHandler(String orderNo){

        orderService.completePay(orderNo);

        return SimpleResponser.success();
    }

    private void checkOrderState(Order order) {

    }

}
