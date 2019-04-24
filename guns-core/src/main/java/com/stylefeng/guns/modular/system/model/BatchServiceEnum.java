package com.stylefeng.guns.modular.system.model;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/18 00:25
 * @Version 1.0
 */
public enum BatchServiceEnum {

    Score(1, "批量成绩"),
    Class(2, "批量开班"),
    Sign(3, "批量报名"),

    NULL(99, "未知业务")
    ;

    public int code;
    public String text;

    BatchServiceEnum(int code, String text){
        this.code = code;
        this.text = text;
    }

    public static BatchServiceEnum instanceOf(Integer value) {
        BatchServiceEnum resultService = NULL;

        for(BatchServiceEnum service : values()){
            if (service.code == value){
                resultService = service;
                break;
            }
        }
        return resultService;
    }
}
