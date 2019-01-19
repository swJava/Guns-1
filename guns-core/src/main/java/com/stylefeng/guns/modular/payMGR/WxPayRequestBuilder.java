package com.stylefeng.guns.modular.payMGR;

import com.stylefeng.guns.modular.system.model.Order;
import com.stylefeng.guns.util.DateUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.util.*;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/3 00:51
 * @Version 1.0
 */
public class WxPayRequestBuilder extends PayRequestBuilder {
    /**
     * 商户号
     */
    private String mchId;

    /**
     * 应用号
     */
    private String appId;

    /**
     * 设备号
     */
    private String deviceId;

    /**
     * 订单信息
     */
    private Order order;

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public PostRequest order(Order merchantOrder) {
        String nonce = randomCode();
        WxPostRequest postRequest = new WxPostRequest(nonce);
        postRequest.putOrderUrl(getOrderUrl());

        Date now = new Date();

        Map<String, Object> postData = new HashMap<String, Object>();
        postData.put("appid", this.appId);
        postData.put("mch_id", this.mchId);
        postData.put("device_info", this.deviceId);
        postData.put("nonce_str", nonce);
        postData.put("body", "");
        postData.put("detail", "");
        postData.put("out_trade_no", merchantOrder.getAcceptNo());
        postData.put("fee_type", "CNY");
        postData.put("total_fee", merchantOrder.getAmount());
        postData.put("spbill_create_ip", "39.98.48.194");
        postData.put("time_start", DateUtil.format(now, "yyyyMMddHHmmss"));
        postData.put("time_expire", DateUtil.format(DateUtil.add(now, Calendar.HOUR, 1), "yyyyMMddHHmmss"));
        postData.put("notify_url", "");

        XStream xStream = new XStream();
        xStream.alias("xml", Map.class);
        xStream.registerConverter(new MapEntryConvert());

        postRequest.setDatagram(xStream.toXML(postData));

        return postRequest;
    }

    class MapEntryConvert implements Converter {

        @Override
        public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext marshallingContext) {
            AbstractMap map = (AbstractMap) value;
            for (Object obj : map.entrySet()) {
                Map.Entry entry = (Map.Entry) obj;
                writer.startNode(entry.getKey().toString());
                writer.setValue(entry.getValue().toString());
                writer.endNode();
            }
        }

        @Override
        public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
            return null;
        }

        @Override
        public boolean canConvert(Class aClass) {
            return AbstractMap.class.isAssignableFrom(aClass);
        }
    }
/*
    public static void main(String[] args){
        Date now = new Date();
        Map<String, Object> postData = new HashMap<String, Object>();
        postData.put("appid", "dfewrwerewrwe");
        postData.put("mch_id", "fdfweeeeeeeeeee");
        postData.put("device_info", "eeeeeeeeeeee");
        postData.put("nonce_str", "nonce_str");
        postData.put("body", "");
        postData.put("detail", "");
        postData.put("out_trade_no", "merchant_order_no");
        postData.put("fee_type", "CNY");
        postData.put("total_fee", 200);
        postData.put("spbill_create_ip", "39.98.48.194");
        postData.put("time_start", DateUtil.format(now, "yyyyMMddHHmmss"));
        postData.put("time_expire", DateUtil.format(DateUtil.add(now, Calendar.HOUR, 1), "yyyyMMddHHmmss"));
        postData.put("notify_url", "");

        XStream xStream = new XStream();
        xStream.alias("xml", Map.class);
        xStream.registerConverter(new WxPayRequestBuilder.MapEntryConvert());

        System.out.println(xStream.toXML(postData));
    }*/
}
