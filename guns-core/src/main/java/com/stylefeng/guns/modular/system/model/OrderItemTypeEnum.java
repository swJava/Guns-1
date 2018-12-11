package com.stylefeng.guns.modular.system.model;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/30 7:43
 * @Version 1.0
 */
public enum OrderItemTypeEnum {
    Course(1, "课程"),
    Coupon(2, "优惠券"),
    Points(3, "积分")
    ;

    public int code;
    public String text;

    OrderItemTypeEnum(int code, String text){
        this.code = code;
        this.text = text;
    }

    public static OrderItemTypeEnum instanceOf(Integer itemObject) {
        OrderItemTypeEnum typeInstance = null;

        for(OrderItemTypeEnum type : values()){
            if (type.code == itemObject){
                typeInstance = type;
                break;
            }
        }
        return typeInstance;
    }
}
