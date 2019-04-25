package com.stylefeng.guns.modular.payMGR.transfer;

import com.stylefeng.guns.modular.system.model.PayMethodEnum;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/30 16:36
 * @Version 1.0
 */
public class UnionNotifier extends PayNotifier{

    UnionNotifier(){
        channel = PayMethodEnum.unionpay;
    }

    @Override
    public boolean paySuccess() {
        return false;
    }

    @Override
    public String getOrder() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
