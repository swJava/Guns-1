package com.stylefeng.guns.modular.orderMGR;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/3 7:28
 * @Version 1.0
 */
public enum OrderServiceTypeEnum {
    Order("报名"),
    Cancel("取消"),
    NULL("未知")
    ;
    // TODO 调课、调班是否要走订单系统呢？

    public String text;

    OrderServiceTypeEnum(String text){
        this.text = text;
    }

    public static OrderServiceTypeEnum instanceOf(String service) {
        OrderServiceTypeEnum serviceType = NULL;

        if (null == service)
            return serviceType;

        for(OrderServiceTypeEnum type : values()){
            if (type.name().equals( service )) {
                serviceType = type;
                break;
            }
        }

        return serviceType;
    }
}
