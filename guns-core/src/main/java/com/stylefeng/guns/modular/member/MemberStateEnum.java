package com.stylefeng.guns.modular.member;


/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/24 15:41
 * @Version 1.0
 */
public enum MemberStateEnum {
    Valid( 11 , "有效"),
    Invalid( 10, "失效"),
    Lock( 12, "锁定")
    ;

    public int code;
    public String text;

    MemberStateEnum (int code, String text){
        this.code = code;
        this.text = text;
    }
}
