package com.stylefeng.guns.rest.modular.pay.responser;

import com.stylefeng.guns.rest.core.SimpleResponser;
import com.stylefeng.guns.util.MD5Util;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/20 15:36
 * @Version 1.0
 */
@ApiModel(value = "SignResponser", description = "支付签名返回")
public class SignResponser extends SimpleResponser {
    @ApiModelProperty(name = "data", value = "签名返回")
    private SignResult data;

    public SignResult getData() {
        return data;
    }

    public void setData(SignResult data) {
        this.data = data;
    }

    public static SignResponser me(String signType, String sign){
        SignResponser response = new SignResponser();
        response.setCode(SUCCEED);
        response.setMessage("处理成功");

        SignResult result = new SignResult();
        result.setSign(sign);
        String uuid = UUID.randomUUID().toString();
        String random = MD5Util.encrypt(uuid).toUpperCase();
        result.setRandom(random);
        result.setSignType(signType);

        response.setData(result);

        return response;
    }

    static class SignResult {
        String sign;
        String random;
        String signType;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getRandom() {
            return random;
        }

        public void setRandom(String random) {
            this.random = random;
        }

        public String getSignType() {
            return signType;
        }

        public void setSignType(String signType) {
            this.signType = signType;
        }
    }
}
