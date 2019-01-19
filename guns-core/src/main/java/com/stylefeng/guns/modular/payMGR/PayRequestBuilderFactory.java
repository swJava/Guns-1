package com.stylefeng.guns.modular.payMGR;

import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.system.model.Dict;
import com.stylefeng.guns.modular.system.model.Order;
import com.stylefeng.guns.modular.system.model.PayMethodEnum;
import com.stylefeng.guns.modular.system.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/3 00:45
 * @Version 1.0
 */
public class PayRequestBuilderFactory {

    @Autowired
    private IDictService dictService;

    public PayRequestBuilder select(PayMethodEnum payChannel) {

        if (PayMethodEnum.NULL.equals(payChannel))
            throw new ServiceException(MessageConstant.MessageCode.PAY_METHOD_NOT_FOUND);

        PayRequestBuilder builder = null;
        switch(payChannel){
            case weixin:
                builder = createWeixinPayRequestBuilder();
                break;
            case unionpay:
                builder = new UnionPayRequestBuilder();
                break;
            default:
                break;
        }

        return builder;
    }

    private PayRequestBuilder createWeixinPayRequestBuilder() {

        List<Dict> weixinPayArguments = dictService.selectByParentCode("weixin_pay");

        WxPayRequestBuilder builder = new WxPayRequestBuilder();

        for(Dict dict : weixinPayArguments) {
            String property = dict.getName();

            switch(property) {
                case "app_id":
                    builder.setAppId(dict.getCode());
                    break;
                case "mch_id":
                    builder.setMchId(dict.getCode());
                    break;
                case "device_id":
                    builder.setDeviceId(dict.getCode());
                    break;
                case "order_url":
                    builder.setOrderUrl(dict.getCode());
                    break;
            }
        }

        return builder ;
    }
}
