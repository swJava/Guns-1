package com.stylefeng.guns.modular.system.model;

/**
 * 班级报名方式
 *
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/29 15:34
 * @Version 1.0
 */
public enum ClassSignAbility {

    NORMAL(1, "普通报名"),
    CROSS(2, "跨报"),
    UNKNOWN(99, "未知")
    ;

    public int code;
    private String text;

    ClassSignAbility(int code, String text){
        this.code = code;
        this.text = text;
    }

    public static ClassSignAbility instanceOf(int value) {

        ClassSignAbility instance = UNKNOWN;
        for(ClassSignAbility classSignType : values()){
            if (value == classSignType.code) {
                instance = classSignType;
                break;
            }
        }
        return instance;
    }
}
