package com.stylefeng.guns.modular.payMGR;

import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.system.model.PayMethodEnum;

import java.util.Properties;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/3 00:45
 * @Version 1.0
 */
public class PayRequestBuilderFactory {

    private Properties weixinProperties;

    private Properties unionProperties;

    public void setWeixinProperties(Properties weixinProperties) {
        this.weixinProperties = weixinProperties;
    }

    public void setUnionProperties(Properties unionProperties) {
        this.unionProperties = unionProperties;
    }

    public PayRequestBuilder select(PayMethodEnum payChannel) {

        if (PayMethodEnum.NULL.equals(payChannel))
            throw new ServiceException(MessageConstant.MessageCode.PAY_METHOD_NOT_FOUND);

        PayRequestBuilder builder = null;
        switch(payChannel){
            case weixin:
                builder = createWeixinPayRequestBuilder();
                break;
            case unionpay:
                builder = createUnionPayRequestBuilder();
                break;
            default:
                break;
        }

        return builder;
    }

    private PayRequestBuilder createUnionPayRequestBuilder() {
        return new UnionPayRequestBuilder(unionProperties);
    }

    private PayRequestBuilder createWeixinPayRequestBuilder() {
        return new WxPayRequestBuilder(weixinProperties);
    }
}
