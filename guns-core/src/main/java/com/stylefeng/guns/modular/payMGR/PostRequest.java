package com.stylefeng.guns.modular.payMGR;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;

import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/8 01:25
 * @Version 1.0
 */
public abstract class PostRequest {

    private Map<String, String> postUrls = new HashMap<String, String>();

    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 请求报文
     */
    private String datagram;

    public PostRequest(String seq){
        this.nonceStr = seq;
    }

    public void putOrderUrl(String url){
        postUrls.put("order", url);
    }

    public void putQueryUrl(String url){
        postUrls.put("query", url);
    }

    public void setDatagram(String datagram) {
        this.datagram = datagram;
    }

    public abstract void post(ResponseHandler<String> callback);

    @Override
    public String toString() {
        return this.nonceStr;
    }

    @Override
    public boolean equals(Object obj) {
        return this.nonceStr.equals(obj.toString());
    }
}
