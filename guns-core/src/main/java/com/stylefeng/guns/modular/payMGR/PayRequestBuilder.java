package com.stylefeng.guns.modular.payMGR;

import com.stylefeng.guns.modular.system.model.Order;
import com.stylefeng.guns.util.MD5Util;
import org.apache.http.client.methods.RequestBuilder;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/8 00:34
 * @Version 1.0
 */
public abstract class PayRequestBuilder {
    /**
     * 支付订单接口
     */
    private String orderUrl;


    protected String randomCode(){
        String uuid = UUID.randomUUID().toString();
        return MD5Util.encrypt(uuid).toUpperCase();
    }

    public String getOrderUrl() {
        return orderUrl;
    }

    public void setOrderUrl(String orderUrl) {
        this.orderUrl = orderUrl;
    }

    /**
     * 下支付订单
     *
     * 商户系统先调用该接口在微信支付服务后台生成预支付交易单，返回正确的预支付交易会话标识后再在APP里面调起支付。
     * @param merchantOrder
     * @return
     */
    public abstract PostRequest order(Order merchantOrder);


}
