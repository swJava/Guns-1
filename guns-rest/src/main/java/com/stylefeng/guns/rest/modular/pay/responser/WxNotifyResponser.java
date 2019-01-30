package com.stylefeng.guns.rest.modular.pay.responser;

import com.stylefeng.guns.modular.payMGR.MapEntryConvert;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/29 16:25
 * @Version 1.0
 */
public class WxNotifyResponser {

    private static final String SUCCESS_CODE = "SUCCESS";
    private static final String SUCCESS_MESSAGE = "OK";

    private String return_code;

    private String return_msg;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public static WxNotifyResponser success() {

        WxNotifyResponser response = new WxNotifyResponser();
        response.setReturn_code(SUCCESS_CODE);
        response.setReturn_msg(SUCCESS_MESSAGE);

        return response;
    }

    @Override
    public String toString() {
        XStream xStream = new XStream(new StaxDriver(new NoNameCoder()));
        xStream.alias("xml", Map.class);
        xStream.registerConverter(new MapEntryConvert());

        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("return_code", this.return_code);
        resultMap.put("return_msg", this.return_msg);
        return StringEscapeUtils.unescapeXml(xStream.toXML(resultMap));
    }
}
