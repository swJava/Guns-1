package com.stylefeng.guns.jwt;

import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.rest.modular.auth.converter.BaseTransferEntity;
import com.stylefeng.guns.rest.modular.auth.security.impl.Base64SecurityAction;
import com.stylefeng.guns.rest.modular.member.requester.RegistRequester;
import com.stylefeng.guns.util.MD5Util;

/**
 * jwt测试
 *
 * @author fengshuonan
 * @date 2017-08-21 16:34
 */
public class DecryptTest {

    public static void main(String[] args) {

        String salt = "0iqwhi";

        RegistRequester requester = new RegistRequester();
        requester.setUserName("18580255110");
        requester.setPassword("e9cee71ab932fde863338d08be4de9dfe39ea049bdafb342ce659ec5450b69ae");
        requester.setCaptcha("123456");
        requester.setGrade(1);

        String jsonString = JSON.toJSONString(requester);
        String encode = new Base64SecurityAction().doAction(jsonString);
        String md5 = MD5Util.encrypt(encode + salt);

        BaseTransferEntity baseTransferEntity = new BaseTransferEntity();
        baseTransferEntity.setObject(encode);
        baseTransferEntity.setSign(md5);

        System.out.println(JSON.toJSONString(baseTransferEntity));
    }
}
