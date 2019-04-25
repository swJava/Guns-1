package com.stylefeng.guns.rest.modular.order.responser;

import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/30 8:18
 * @Version 1.0
 */
@ApiModel(value = "OrderPostResponser", description = "订单提交返回")
public class OrderPostResponser extends SimpleResponser {

    @ApiModelProperty(name = "data", value = "提交返回")
    private OrderData data;

    public OrderData getData() {
        return data;
    }

    public void setData(OrderData data) {
        this.data = data;
    }

    public static OrderPostResponser me(String orderNo, String paySequence) {
        OrderPostResponser response = new OrderPostResponser();
        response.setCode(SUCCEED);
        response.setMessage("处理成功");

        OrderData data = new OrderData();
        data.setOrderNo(orderNo);
        data.setPaySequence(paySequence);
        response.setData(data);
        return response;
    }

    @ApiModel
    static class OrderData {
        @ApiModelProperty(value = "订单号", example = "OD181130202020022")
        private String orderNo;
        @ApiModelProperty(value = "支付流水", example = "wx201410272009395522657a690389285100")
        private String paySequence;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getPaySequence() {
            return paySequence;
        }

        public void setPaySequence(String paySequence) {
            this.paySequence = paySequence;
        }
    }
}
