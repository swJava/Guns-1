package com.stylefeng.guns.rest.modular.order.responser;

import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/1 8:43
 * @Version 1.0
 */
@ApiModel(value = "OrderListResponser", description = "订单列表")
public class OrderListResponser extends SimpleResponser {
    @ApiModelProperty(name = "data", value = "选课单集合")
    private List<Order> data;

    public List<Order> getData() {
        return data;
    }

    public void setData(List<Order> data) {
        this.data = data;
    }

    public static Responser me(List<Order> orderList) {
        OrderListResponser response = new OrderListResponser();
        response.setCode(SUCCEED);
        response.setMessage("查询成功");
        response.setData(orderList);
        return response;
    }

    @ApiModel
    class OrderDto extends Order {
        @ApiModelProperty(name = "classInfo", value = "班级信息")
        ClassDto classInfo;


    }

    @ApiModel
    class ClassDto extends com.stylefeng.guns.modular.system.model.Class {

        @ApiModelProperty(name = "classTimeDesp", value = "上课时间描述", example = "每周五、周六 09:00 ~ 10:30")
        String classTimeDesp;

        @ApiModelProperty(name = "canAdjust", value = "能否调课", example = "false")
        boolean canAdjust;

        @ApiModelProperty(name = "canChange", value = "能否转班", example = "true")
        boolean canChange;

        public String getClassTimeDesp() {
            return classTimeDesp;
        }

        public void setClassTimeDesp(String classTimeDesp) {
            this.classTimeDesp = classTimeDesp;
        }

        public boolean isCanAdjust() {
            return canAdjust;
        }

        public void setCanAdjust(boolean canAdjust) {
            this.canAdjust = canAdjust;
        }

        public boolean isCanChange() {
            return canChange;
        }

        public void setCanChange(boolean canChange) {
            this.canChange = canChange;
        }
    }
}
