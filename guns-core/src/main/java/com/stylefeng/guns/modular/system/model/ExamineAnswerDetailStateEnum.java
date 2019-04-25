package com.stylefeng.guns.modular.system.model;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/2/24 17:03
 * @Version 1.0
 */
public enum ExamineAnswerDetailStateEnum {
    Right(1, "正确"),
    Wrong(2, "错误")
    ;

    public int code;
    public String text;

    ExamineAnswerDetailStateEnum(int code, String text){
        this.code = code;
        this.text = text;
    }
}
