package com.stylefeng.guns.rest.modular.order.responser;

import com.stylefeng.guns.rest.modular.education.responser.ClassDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/16 21:40
 * @Version 1.0
 */
@ApiModel(value = "OrderDto", description = "订单扩展")
public class OrderDto {
    @ApiModelProperty(name = "classInfo", value = "班级信息")
    private ClassDto classInfo;

    public ClassDto getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassDto classInfo) {
        this.classInfo = classInfo;
    }
}
