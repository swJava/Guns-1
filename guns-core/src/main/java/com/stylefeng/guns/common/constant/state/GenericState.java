package com.stylefeng.guns.common.constant.state;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/15 9:50
 * @Version 1.0
 */
public enum GenericState {
    Valid(1, "启用"),
    Invalid(0, "禁用")
    ;

    public int code;
    public String text;

    GenericState(int code, String text){
        this.code = code;
        this.text = text;
    }
}
