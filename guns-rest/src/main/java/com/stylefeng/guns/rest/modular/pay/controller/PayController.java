package com.stylefeng.guns.rest.modular.pay.controller;

import com.stylefeng.guns.modular.orderMGR.service.IOrderService;
import com.stylefeng.guns.modular.system.model.Order;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.modular.pay.requester.PayCompleteRequester;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by 罗华.
 */
@RestController
@RequestMapping("/pay")
@Api(tags = "支付接口")
public class PayController {

    @Autowired
    private IOrderService orderService;

    @ApiOperation(value="支付完成", httpMethod = "POST")
    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    public Responser doComplete(
            @RequestBody
            @NotNull(message = "缺少请求参数")
            @Valid
            PayCompleteRequester requester){

        Order order = orderService.get(requester.getOrderNo());
        return null;
    }

    @ApiOperation(value="微信支付完成", httpMethod = "POST")
    @RequestMapping(value = "/weixin/complete", method = RequestMethod.POST)
    public Responser weixinComplete(PayCompleteRequester requester){
        return null;
    }

    @ApiOperation(value="银联支付完成", httpMethod = "POST")
    @RequestMapping(value = "/bank/complete", method = RequestMethod.POST)
    public Responser bankComplete(PayCompleteRequester requester){
        return null;
    }


}
