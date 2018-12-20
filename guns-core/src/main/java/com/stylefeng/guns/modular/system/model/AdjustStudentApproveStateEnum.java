package com.stylefeng.guns.modular.system.model;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/20 15:58
 * @Version 1.0
 */
public enum AdjustStudentApproveStateEnum {

    Create(10, "新建"),
    Appove(11, "审核通过"),
    Refuse(12, "打回")
    ;

    public int code;
    public String text;

    AdjustStudentApproveStateEnum(int code, String text){
        this.code = code;
        this.text = text;
    }
}
