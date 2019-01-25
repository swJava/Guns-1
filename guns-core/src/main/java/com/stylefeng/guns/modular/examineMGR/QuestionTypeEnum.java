package com.stylefeng.guns.modular.examineMGR;

/**
 * 试题类型
 *
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/30 16:59
 * @Version 1.0
 */
public enum QuestionTypeEnum {
    Select(1, "选择题"),
    Fill(2, "填空题"),
    Subject(3, "主观题")
    ;

    public int code;
    public String text;

    QuestionTypeEnum(int code, String text){
        this.code = code;
        this.text = text;
    }
}
