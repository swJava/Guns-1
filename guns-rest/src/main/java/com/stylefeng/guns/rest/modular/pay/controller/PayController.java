package com.stylefeng.guns.rest.modular.pay.controller;

import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.modular.pay.requester.PayRequester;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 罗华.
 */
@RestController
@RequestMapping("/pay")
@Api(tags = "支付接口")
public class PayController {

    @ApiOperation(value="微信支付", httpMethod = "POST")
    @RequestMapping("/weixin")
    public Responser 微信支付(PayRequester requester){
        return null;
    }

    @ApiOperation(value="银联支付", httpMethod = "POST")
    @RequestMapping("/bank")
    public Responser 银联支付(PayRequester requester){
        return null;
    }
}
