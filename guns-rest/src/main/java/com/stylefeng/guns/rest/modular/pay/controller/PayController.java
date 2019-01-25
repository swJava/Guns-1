package com.stylefeng.guns.rest.modular.pay.controller;

import com.stylefeng.guns.modular.orderMGR.service.IOrderService;
import com.stylefeng.guns.modular.payMGR.MapEntryConvert;
import com.stylefeng.guns.modular.payMGR.WxPayRequestBuilder;
import com.stylefeng.guns.rest.core.ApiController;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import com.stylefeng.guns.rest.modular.pay.responser.RandomResponser;
import com.stylefeng.guns.rest.modular.pay.responser.SignResponser;
import com.stylefeng.guns.util.MD5Util;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by 罗华.
 */
@RestController
@RequestMapping("/pay")
@Api(tags = "支付接口")
public class PayController extends ApiController {
    private static final Logger log = LoggerFactory.getLogger(PayController.class);

    @Autowired
    private IOrderService orderService;

    @Value("${application.pay.weixin.app-secret:''}")
    private String weixinSecret;

    @Value("${application.pay.union.app-secret:''}")
    private String unionSecret;

    @ApiOperation(value="微信支付签名", httpMethod = "POST", response = SignResponser.class)
    @RequestMapping(value = "/weixin/sign", method = RequestMethod.POST)
    public Responser weixinSign(@RequestBody Map<String, Object> requestParams){
        String signCode = sign(weixinSecret, requestParams);
        return SignResponser.me("MD5", signCode);
    }


    @ApiOperation(value="银联支付签名", httpMethod = "POST", response = SignResponser.class)
    @RequestMapping(value = "/union/sign", method = RequestMethod.POST)
    public Responser unionSign(@RequestBody Map<String, Object> requestParams){
        String signCode = sign(unionSecret, requestParams);
        return SignResponser.me("MD5", signCode);
    }

    @ApiOperation(value="微信支付随机数", httpMethod = "POST", response = RandomResponser.class)
    @RequestMapping(value = "/weixin/random", method = RequestMethod.POST)
    public Responser weixinRandom(){
        String uuid = UUID.randomUUID().toString();
        String random = MD5Util.encrypt(uuid).toUpperCase();
        return RandomResponser.me(random);
    }

    @RequestMapping(value = "/weixin/notify", method = RequestMethod.POST)
    public String payNotifyHandler(@RequestBody String notifyMessage){

        System.out.println(notifyMessage);

        Map<String, Object> response = new HashMap<String, Object>();
        response.put("return_code", "SUCCESS");
        response.put("return_msg", "OK");

        XStream xStream = new XStream(new StaxDriver(new NoNameCoder()));
        xStream.alias("xml", Map.class);
        xStream.registerConverter(new MapEntryConvert());

        return xStream.toXML(response);
    }


    private String sign(String signKey, Map<String, Object> postData) {
        Set<String> postKeySet = new TreeSet<String>();
        Iterator<String> postKeyIter = postData.keySet().iterator();
        while(postKeyIter.hasNext()){
            postKeySet.add(postKeyIter.next());
        }

        Iterator<String> sortedKeyIter = postKeySet.iterator();
        StringBuilder stringSignBuilder = new StringBuilder();
        while(sortedKeyIter.hasNext()){
            String key = sortedKeyIter.next();
            Object value = postData.get(key);
            if (null == value)
                continue;

            if (stringSignBuilder.length() > 0)
                stringSignBuilder.append("&");

            stringSignBuilder.append(key + "=" + value);
        }

        stringSignBuilder.append("&key=").append(signKey);
        String sign = MD5Util.encrypt(stringSignBuilder.toString()).toUpperCase();
        log.debug("sign ===> {}", sign);
        return sign;
    }
}
