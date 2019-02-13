package com.stylefeng.guns.modular.system.model;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/2/13 11:21
 * @Version 1.0
 */
public enum ClassExaminableEnum {

    YES ( 1, "开放报名"),
    NO ( 0, "关闭报名"),
    UNKNOWN (99, "未知")
    ;

    public int code;
    public String text;

    ClassExaminableEnum(int code, String text){
        this.code = code;
        this.text = text;
    }

    public static ClassExaminableEnum instanceOf(int value) {

        ClassExaminableEnum instance = UNKNOWN;
        for(ClassExaminableEnum classExaminable : values()){
            if (value == classExaminable.code) {
                instance = classExaminable;
                break;
            }
        }
        return instance;
    }

}
