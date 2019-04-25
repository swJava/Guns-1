package com.stylefeng.guns.rest.modular.order.responser;

import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/30 9:00
 * @Version 1.0
 */
@ApiModel(value = "PayOrderResponser", description = "支付下单返回")
public class PayOrderResponser extends SimpleResponser {
    @ApiModelProperty(name = "sequence", value = "支付订单流水")
    private String sequence;

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public static Responser me(String paySequence) {
        PayOrderResponser response = new PayOrderResponser();
        response.setCode(SUCCEED);
        response.setMessage("处理成功");

        response.setSequence(paySequence);
        return response;
    }
}
