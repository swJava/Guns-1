package com.stylefeng.guns.modular.system.model;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/22 16:55
 * @Version 1.0
 */
public enum ClassAblilityEnum {
    Ablility_1 (1, "启航"),
    Ablility_2 (2, "敏学"),
    Ablility_3 (3, "勤思"),
    Ablility_4 (4, "创新"),
    Ablility_5 (5, "诊断"),
    Ablility_99 (99, "其他")
    ;

    public int code;
    public String text;

    ClassAblilityEnum(int code, String text){
        this.code = code;
        this.text = text;
    }

    public static ClassAblilityEnum instanceOf(Integer value) {
        ClassAblilityEnum result = ClassAblilityEnum.Ablility_99;

        for(ClassAblilityEnum ablility : values()){
            if (ablility.code == value){
                result = ablility;
                break;
            }
        }

        return result;
    }
}
