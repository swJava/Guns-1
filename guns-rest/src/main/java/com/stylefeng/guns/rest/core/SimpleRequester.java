package com.stylefeng.guns.rest.core;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stylefeng.guns.rest.core.Requester;
import io.swagger.annotations.ApiModelProperty;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HH on 2016/4/7.
 */
public abstract class SimpleRequester implements Requester {
    private static final long serialVersionUID = -3378156397010633473L;

    private static final String ILLEGAL_PARAMETER_TIP = "非法参数： %s - %s";

    private static final String SPLITTER = ";";
    @ApiModelProperty(hidden = true)
    protected String[] messages = new String[0];
    @ApiModelProperty(hidden = true)
    protected String stateCode;


    public abstract boolean checkValidate();

    /**
     * 获取状态码
     * @return
     */
    public String getStateCode(){
        return this.stateCode;
    }

    /**
     * 获取状态信息
     * @return
     */
    @ApiModelProperty(hidden = true)
    public final String getMessage(){
        StringBuffer msgBuff = new StringBuffer();

        for(String msg : this.messages){
            msgBuff.append(msg).append(SPLITTER);
        }

        return msgBuff.toString();
    }

    /**
     *
     * @return
     */
    public Map<String, Object> toMap(){

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(this);

                    map.put(key, value);
                }

            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }

        return map;
    }
    public String toJsonString(){
        ObjectMapper mapper = new ObjectMapper();
        String sendData = null;
        try {
            sendData = mapper.writeValueAsString(this);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sendData;
    }

    public final void addMessage(String message){
        if (null == message)
            return;

        if (null == messages)
            this.messages = new String[0];

        String[] tempMsg = new String[this.messages.length + 1];
        System.arraycopy(this.messages, 0, tempMsg, 0, this.messages.length);

        tempMsg[this.messages.length] = message;

        this.messages = tempMsg;
    }

    protected String buildIllegalParameter(String paramName, String msg) {
        return String.format(ILLEGAL_PARAMETER_TIP, paramName, msg);
    }

}
