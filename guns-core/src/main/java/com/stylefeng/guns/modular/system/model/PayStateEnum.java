package com.stylefeng.guns.modular.system.model;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/15 11:21
 * @Version 1.0
 */
public enum PayStateEnum {
    NoPay(1, "待支付"),
    PayOk(2, "已支付"),
    Expire(3, "已超时")
    ;

    public int code;
    public String text;

    PayStateEnum(int code, String text){
        this.code = code;
        this.text = text;
    }
}
