package com.stylefeng.guns.modular.payMGR.transfer;

import com.stylefeng.guns.modular.system.model.PayMethodEnum;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/30 16:37
 * @Version 1.0
 */
public abstract class PayNotifier {
    /**
     * 支付渠道
     */
    protected PayMethodEnum channel;

    public PayMethodEnum getChannel() {
        return channel;
    }

    /**
     * 是否支付成功
     *
     * @return
     */
    public abstract boolean paySuccess();

    /**
     * 获取商户订单号
     *
     * @return
     */
    public abstract String getOrder();

    /**
     * 获取支付通知信息
     *
     * @return
     */
    public abstract String getMessage();
}
