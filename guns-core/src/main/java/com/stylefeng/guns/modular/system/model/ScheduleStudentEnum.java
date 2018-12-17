package com.stylefeng.guns.modular.system.model;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/17 10:00
 * @Version 1.0
 */
public enum ScheduleStudentEnum {
    Valid(1, "有效"),
    InValid(-1, "已失效")
    ;

    public int code;
    public String text;

    ScheduleStudentEnum(int code, String text){
        this.code = code;
        this.text = text;
    }
}
