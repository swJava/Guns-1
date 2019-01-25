package com.stylefeng.guns.modular.system.model;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/21 20:20
 * @Version 1.0
 */
public enum QuestionAutoMarkingEnum {

    Yes(1, "自动阅卷"),
    No(0, "人工阅卷")
    ;

    public int code;
    public String text;

    QuestionAutoMarkingEnum (int code, String text){
        this.code = code;
        this.text = text;
    }
}
