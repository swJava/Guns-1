package com.stylefeng.guns.modular.system.model;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/23 15:01
 * @Version 1.0
 */
public enum ExamineAnswerStateEnum {

    Create(0, "新建"),
    Testing(1, "测试中"),
    Pause(2, "测试中"),
    Sumit(3, "已交卷"),
    Finish(4, "已批改"),

    Null(99, "未知")
    ;

    public int code;
    public String text;

    ExamineAnswerStateEnum(int code, String text){
        this.code = code;
        this.text = text;
    }


    public static ExamineAnswerStateEnum instanceOf(int value) {
        ExamineAnswerStateEnum result = ExamineAnswerStateEnum.Null;

        for(ExamineAnswerStateEnum state : values()){
            if (state.code == value){
                result = state;
                break;
            }
        }

        return result;
    }
}
