package com.stylefeng.guns.rest.modular.pay.controller;

import com.stylefeng.guns.modular.orderMGR.service.IOrderService;
import com.stylefeng.guns.rest.core.ApiController;
import com.stylefeng.guns.rest.core.Responser;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

    public Responser payNotifyHandler(){
        return null;
    }

}
