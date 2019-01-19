package com.stylefeng.guns.modular.payMGR.service.impl;

import com.stylefeng.guns.modular.payMGR.PayRequestBuilderFactory;
import com.stylefeng.guns.modular.payMGR.service.IPayRequestService;
import com.stylefeng.guns.modular.payMGR.service.IPayResultService;
import com.stylefeng.guns.modular.payMGR.service.IPayService;
import com.stylefeng.guns.modular.system.model.Order;
import com.stylefeng.guns.modular.system.model.PayMethodEnum;
import com.stylefeng.guns.modular.system.service.IDictService;
import com.stylefeng.guns.modular.system.service.impl.CaptchaServiceImpl;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/27 15:15
 * @Version 1.0
 */
@Service
public class PayServiceImpl implements IPayService {
    private static final Logger log = LoggerFactory.getLogger(PayServiceImpl.class);

    @Autowired
    private IPayRequestService payRequestService;
    @Autowired
    private IPayResultService payResultService;
    @Autowired
    private IDictService dictService;
    @Autowired(required = false)
    private PayRequestBuilderFactory builderFactory;

    @Override
    public String createPayOrder(Order order) {

        if (null == builderFactory) {
            log.warn("no PayRequestBuilderFactory found, use test mode");
            return UUID.randomUUID().toString().replaceAll("-", "");
        }

        PayMethodEnum payChannel = PayMethodEnum.instanceOf(order.getPayMethod());

        if (null == payChannel) {
            log.warn("No pay channel supoort");
            return UUID.randomUUID().toString().replaceAll("-", "");
        }

        builderFactory.select(payChannel).order(order).post(new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                return null;
            }
        });

        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
