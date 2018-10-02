package com.stylefeng.guns.modular.student.common;

/**
 * 账号类型枚举
 * @author: simple.song
 * Date: 2018/10/2 Time: 15:14
 */
public enum TypeEnum {

    TYPE_STUDENT(1,"学生"),
    TYPE_TEACHER(2,"老师")
    ;

    private int code;
    private String msg;


    TypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}