package com.stylefeng.guns.modular.payMGR.service.impl;

import com.stylefeng.guns.modular.payMGR.service.IPayRequestService;
import com.stylefeng.guns.modular.payMGR.service.IPayResultService;
import com.stylefeng.guns.modular.payMGR.service.IPayService;
import com.stylefeng.guns.modular.system.model.Order;
import com.stylefeng.guns.modular.system.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/27 15:15
 * @Version 1.0
 */
@Service
public class PayServiceImpl implements IPayService {
    @Autowired
    private IPayRequestService payRequestService;
    @Autowired
    private IPayResultService payResultService;

    @Override
    public String createPayOrder(Order order) {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
