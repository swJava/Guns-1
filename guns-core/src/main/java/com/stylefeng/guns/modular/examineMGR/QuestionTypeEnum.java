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
    SS(1, "单选题"),
    MS(2, "多选题"),
    FQ(3, "填空题"),
    SQ(4, "主观题"),
    UN(99, "未知题型")
    ;

    public int code;
    public String text;

    QuestionTypeEnum(int code, String text){
        this.code = code;
        this.text = text;
    }

    public static QuestionTypeEnum instanceOf(Integer value) {
        QuestionTypeEnum questionType = UN;

        for(QuestionTypeEnum type : values()){
            if (type.code == value){
                questionType = type;
                break;
            }
        }

        return questionType;
    }
}
