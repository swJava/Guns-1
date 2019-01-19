package com.stylefeng.guns.modular.payMGR;

import org.apache.http.client.ResponseHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/8 01:26
 * @Version 1.0
 */
public class WxPostRequest extends PostRequest {

    public WxPostRequest(String seq) {
        super(seq);
    }

    @Override
    public void post(ResponseHandler<String> callback) {

    }
}
