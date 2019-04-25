package com.stylefeng.guns.modular.system.model;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/20 15:44
 * @Version 1.0
 */
public enum AdjustStudentTypeEnum {
    Adjust(1, "调课"),
    Change(2, "转班")
    ;

    public int code;
    public String text;

    AdjustStudentTypeEnum(int code, String text){
        this.code = code;
        this.text = text;
    }
}
