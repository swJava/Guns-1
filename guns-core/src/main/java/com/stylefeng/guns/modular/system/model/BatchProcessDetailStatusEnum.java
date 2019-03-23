package com.stylefeng.guns.modular.system.model;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/23 20:49
 * @Version 1.0
 */
public enum BatchProcessDetailStatusEnum {

    Create(0, "待处理"),
    Working(2, "处理中"),
    Reject(3, "处理失败"),
    Pass(4, "处理完成"),

    UNDEFINED(99, "未知")
    ;

    public int code;
    public String text;

    BatchProcessDetailStatusEnum(int code, String text){
        this.code = code;
        this.text = text;
    }

    public static BatchProcessDetailStatusEnum instanceOf(Integer state) {

        BatchProcessDetailStatusEnum resultState = UNDEFINED;

        for(BatchProcessDetailStatusEnum status : values()){
            if (status.code == state){
                resultState = status;
                break;
            }
        }

        return resultState;
    }
}
