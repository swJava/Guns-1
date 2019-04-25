package com.stylefeng.guns.modular.system.model;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/16 0:04
 * @Version 1.0
 */
public enum CourseCartStateEnum {

    Invalid(-1, "失效"),
    Valid(0, "有效"),
    Ordered(1, "已生成订单")
    ;

    public int code;
    public String text;

    CourseCartStateEnum(int code, String text){
        this.code = code;
        this.text = text;
    }
}
