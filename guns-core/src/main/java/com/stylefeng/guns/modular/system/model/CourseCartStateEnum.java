package com.stylefeng.guns.modular.system.model;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/16 0:04
 * @Version 1.0
 */
public enum CourseCartStateEnum {

    Invalid(0, "失效"),
    Valid(1, "有效"),
    Ordered(2, "已生成订单")
    ;

    public int code;
    public String text;

    CourseCartStateEnum(int code, String text){
        this.code = code;
        this.text = text;
    }
}
