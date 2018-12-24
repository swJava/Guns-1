package com.stylefeng.guns.modular.system.model;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/24 23:45
 * @Version 1.0
 */
public enum ContentPublishTypeEnum {

    Original(1, "原创"),
    Reference(2, "引用")
    ;

    public int code;
    public String text;

    ContentPublishTypeEnum (int code , String text){
        this.code = code;
        this.text = text;
    }
}
