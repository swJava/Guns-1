package com.stylefeng.guns.modular.education;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/2 23:51
 * @Version 1.0
 */
public enum CourseMethodEnum {
    Local(1, "面授"),
    Remote(2, "在线"),
    Double(3, "双师"),
    NULL(-1, "未知")
    ;

    public int code;
    public String text;

    CourseMethodEnum(int code, String text){
        this.code = code;
        this.text = text;
    }

    public static CourseMethodEnum instanceOf(Integer code) {
        CourseMethodEnum matched = NULL;
        for (CourseMethodEnum method : values()){
            if (code == method.code) {
                matched = method;
                break;
            }
        }

        return matched;
    }
}
