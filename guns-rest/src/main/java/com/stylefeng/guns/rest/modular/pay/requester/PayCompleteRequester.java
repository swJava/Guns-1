package com.stylefeng.guns.rest.modular.pay.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 罗华.
 */
@ApiModel(value = "", description = "支付完成请求")
public class PayCompleteRequester extends SimpleRequester {
    private static final long serialVersionUID = -6607145030998116256L;
    /**
     * 订单号
     */
    @ApiModelProperty(name = "orderNo", value = "订单号", example = "18580255110")
    private String orderNo;
    @ApiModelProperty(name = "payResult", value = "支付结果")
    private PayResultRequester payResult;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public PayResultRequester getPayResult() {
        return payResult;
    }

    public void setPayResult(PayResultRequester payResult) {
        this.payResult = payResult;
    }

    @Override
    public boolean checkValidate() {
        return false;
    }
}
