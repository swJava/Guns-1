package com.stylefeng.guns.fastjson;

import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.rest.modular.auth.converter.BaseTransferEntity;
import com.stylefeng.guns.rest.modular.member.requester.RegistRequester;
import com.stylefeng.guns.util.MD5Util;

/**
 * json测试
 *
 * @author fengshuonan
 * @date 2017-08-25 16:11
 */


public class JsonTest {

    public static void main(String[] args) {
        String randomKey = "1xm7hw";

        BaseTransferEntity baseTransferEntity = new BaseTransferEntity();
        RegistRequester requester = new RegistRequester();
        requester.setUserName("18580255110");
        requester.setPassword("e9cee71ab932fde863338d08be4de9dfe39ea049bdafb342ce659ec5450b69ae");
        baseTransferEntity.setObject("123123");

        String json = JSON.toJSONString(requester);

        //md5签名
        String encrypt = MD5Util.encrypt(json + randomKey);
        baseTransferEntity.setSign(encrypt);

        System.out.println(JSON.toJSONString(baseTransferEntity));
    }
}
