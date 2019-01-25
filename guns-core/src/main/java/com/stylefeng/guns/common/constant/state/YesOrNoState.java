package com.stylefeng.guns.common.constant.state;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/22 10:45
 * @Version 1.0
 */
public enum YesOrNoState {
    Yes(1, "是"),
    No(0, "不是")
    ;

    public int code;
    public String text;

    YesOrNoState(int code, String text){
        this.code = code;
        this.text = text;
    }
}
