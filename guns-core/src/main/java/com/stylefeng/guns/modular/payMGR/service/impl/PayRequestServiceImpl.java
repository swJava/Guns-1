package com.stylefeng.guns.modular.payMGR.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.payMGR.service.IPayRequestService;
import com.stylefeng.guns.modular.system.dao.PayRequestMapper;
import com.stylefeng.guns.modular.system.model.PayRequest;
import org.springframework.stereotype.Service;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/27 15:05
 * @Version 1.0
 */
@Service
public class PayRequestServiceImpl extends ServiceImpl<PayRequestMapper, PayRequest> implements IPayRequestService {
}
