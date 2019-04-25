package com.stylefeng.guns.modular.system.model;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/15 11:21
 * @Version 1.0
 */
public enum PayStateEnum {
    Failed(-1, "支付失败"),
    NoPay(0, "待支付"),
    Paying(1, "已支付"),
    PayOk(2, "支付成功"),
    Expire(3, "已超时")
    ;

    public int code;
    public String text;

    PayStateEnum(int code, String text){
        this.code = code;
        this.text = text;
    }

    public static PayStateEnum instanceOf(int payState) {
        PayStateEnum payStateEnum = null;

        for(PayStateEnum state : values()){
            if (payState == state.code){
                payStateEnum = state;
                break;
            }
        }
        return payStateEnum;
    }
}
