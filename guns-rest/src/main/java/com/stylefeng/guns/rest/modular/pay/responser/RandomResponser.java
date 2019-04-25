package com.stylefeng.guns.rest.modular.pay.responser;

import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/26 0:01
 * @Version 1.0
 */
@ApiModel(value = "RandomResponser", description = "支付随机数返回")
public class RandomResponser extends SimpleResponser {

    @ApiModelProperty(name = "random", value = "随机数")
    private String random;

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public static Responser me(String random) {
        RandomResponser response = new RandomResponser();
        response.setCode(SUCCEED);
        response.setMessage("处理成功");

        response.setRandom(random);

        return response;
    }
}
