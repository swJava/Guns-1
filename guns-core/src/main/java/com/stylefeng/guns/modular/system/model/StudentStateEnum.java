package com.stylefeng.guns.modular.system.model;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/17 11:25
 * @Version 1.0
 */
public enum StudentStateEnum {
    Valid(1, "有效"),
    InValid(0, "无效")
    ;

    public int code;
    public String text;

    StudentStateEnum(int code, String text){
        this.code = code;
        this.text = text;
    }
}
