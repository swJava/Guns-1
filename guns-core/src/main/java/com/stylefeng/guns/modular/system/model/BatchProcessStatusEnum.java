package com.stylefeng.guns.modular.system.model;

import com.stylefeng.guns.common.constant.state.GenericState;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/19 00:18
 * @Version 1.0
 */
public enum BatchProcessStatusEnum {

    Create(0, "新建"),
    Prepare(1, "就绪"),
    Working(2, "处理中"),
    Reject(3, "处理失败"),
    Pass(4, "处理完成"),

    UNDEFINED(99, "未知")
    ;

    public int code;
    public String text;

    BatchProcessStatusEnum(int code, String text){
        this.code = code;
        this.text = text;
    }

    public static BatchProcessStatusEnum instanceOf(Integer state) {

        BatchProcessStatusEnum resultState = UNDEFINED;

        for(BatchProcessStatusEnum status : values()){
            if (status.code == state){
                resultState = status;
                break;
            }
        }

        return resultState;
    }
}
