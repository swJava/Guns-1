package com.stylefeng.guns.modular.system.model;

import org.omg.CORBA.UNKNOWN;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/29 14:04
 * @Version 1.0
 */
public enum ClassSignableEnum {

    YES ( 1, "开放报名"),
    NO ( 0, "关闭报名"),
    UNKNOWN (99, "未知")
    ;

    public int code;
    public String text;

    ClassSignableEnum(int code, String text){
        this.code = code;
        this.text = text;
    }

    public static ClassSignableEnum instanceOf(int value) {

        ClassSignableEnum instance = UNKNOWN;
        for(ClassSignableEnum classSignableEnum : values()){
            if (value == classSignableEnum.code) {
                instance = classSignableEnum;
                break;
            }
        }
        return instance;
    }
}
