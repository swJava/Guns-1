package com.stylefeng.guns.modular.system.model;

/**
 * 缴费类型
 *
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/15 23:00
 * @Version 1.0
 */
public enum PayTypeEnum {
    ClassicPay(1, "线下缴费"),

    AppPay(2, "App缴费")
    ;

    public int code;
    public String text;

    PayTypeEnum(int code , String text){
        this.code = code;
        this.text = text;
    }

    public static PayTypeEnum instanceOf(int payType) {
        PayTypeEnum result = AppPay;

        for(PayTypeEnum type : values()){
            if (type.code == payType) {
                result = type;
                break;
            }
        }

        return result;
    }
}
