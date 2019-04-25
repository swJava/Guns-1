package com.stylefeng.guns.modular.system.model;

import com.stylefeng.guns.modular.payMGR.PayRequestBuilderFactory;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/30 7:36
 * @Version 1.0
 */
public enum PayMethodEnum {
    weixin(22, "微信支付"),
    unionpay(21, "银联支付"),
    NULL(-1, "不支持")
    ;

    public int code;
    public String text;

    PayMethodEnum(int code, String text){
        this.code = code;
        this.text = text;
    }

    public static PayMethodEnum instanceOf(Integer method) {
        PayMethodEnum payMethod = NULL;

        if (null == method)
            return payMethod;

        for(PayMethodEnum m : values()){
            if (m.code == method){
                payMethod = m;
                break;
            }
        }
        return payMethod;
    }
}
