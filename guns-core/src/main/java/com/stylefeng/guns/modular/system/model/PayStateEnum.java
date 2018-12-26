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
    Paying(1, "支付请求已发送"),
    PayOk(2, "成功"),
    Expire(3, "已超时")
    ;

    public int code;
    public String text;

    PayStateEnum(int code, String text){
        this.code = code;
        this.text = text;
    }
}
