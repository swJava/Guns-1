package com.stylefeng.guns.rest.modular.pay.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/30 8:42
 * @Version 1.0
 */
@ApiModel(value = "PayOrderRequester", description = "支付下单请求信息")
public class PayOrderRequester extends SimpleRequester {

    @ApiModelProperty(name = "order", required = true, value = "订单号", example = "CRMD1901290106067884")
    @NotBlank(message = "订单号不能为空")
    private String order;
    @ApiModelProperty(name = "channel", required = false, value = "支付渠道， 为空保持原有订单的支付渠道", example = "22")
    private Integer channel;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    @Override
    public boolean checkValidate() {
        return false;
    }
}
