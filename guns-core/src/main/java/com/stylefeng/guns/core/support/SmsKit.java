package com.stylefeng.guns.core.support;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/12 17:52
 * @Version 1.0
 */
public final class SmsKit {
    private static final String SEND_OK = "OK";

    private SmsKit() {
    }

    private SendSmsResponse response;

    private long connectTimeout = 10000;

    private long readTimeout = 10000;

    private String product;

    private String domain;

    private String accessKeyId;

    private String accessKeySecret;

    /**
     * 短信模板编码
     */
    private String smsTemplate;

    /**
     * 短信签名
     */
    private String smsSign;

    /**
     * 系统业务流水
     */
    private String bussId;

    public static SmsKit me() {
        return new SmsKit();
    }

    public SmsKit setConnectTimeout(long timeout) {
        this.connectTimeout = timeout;
        return this;
    }

    public SmsKit setReadTimeout(long timeout) {
        this.readTimeout = timeout;
        return this;
    }

    public SmsKit setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
        return this;
    }

    public SmsKit setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
        return this;
    }

    public SmsKit setProduct(String product) {
        this.product = product;
        return this;
    }

    public SmsKit setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public SmsKit setSmsSign(String smsSign){
        this.smsSign = smsSign;
        return this;
    }

    public SmsKit setSmsTemplate(String smsTemplate){
        this.smsTemplate = smsTemplate;
        return this;
    }

    public SmsKit setBussId(String bussId) {
        this.bussId = bussId;
        return this;
    }

    public SmsKit configProperties(Map<String, Object> properties) {
        if (properties.containsKey("connectTimeout")){
            setConnectTimeout(getLong(properties, "connectTimeout"));
        }

        if (properties.containsKey("readTimeout")){
            setReadTimeout(getLong(properties, "readTimeout"));
        }

        if (properties.containsKey("product")){
            setProduct(getString(properties, "product"));
        }

        if (properties.containsKey("domain")){
            setDomain(getString(properties, "domain"));
        }

        if (properties.containsKey("accessKeyId")){
            setAccessKeyId(getString(properties, "accessKeyId"));
        }

        if (properties.containsKey("accessKeySecret")){
            setAccessKeySecret(getString(properties, "accessKeySecret"));
        }

        if (properties.containsKey("smsSign")){
            setSmsSign(getString(properties, "smsSign"));
        }

        if (properties.containsKey("smsTemplate")){
            setSmsTemplate(getString(properties, "smsTemplate"));
        }

        return this;
    }

    private String getString(Map<String, Object> properties, String key) {
        Object value = properties.get(key);

        return String.valueOf(value);
    }

    private long getLong(Map<String, Object> properties, String key) {
        Object value = properties.get(key);

        long result = 0L;
        if (value instanceof String){
            result = Long.parseLong((String)value);
        }else if (value instanceof Long){
            result = (Long)((Long) value).longValue();
        }else if (value instanceof Integer
                || value instanceof Double
                || value instanceof Float
                ){
            result = Long.valueOf(String.valueOf((Integer) value));
        }

        return result;
    }

    public void sendSms(String number, String parameter) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(this.connectTimeout));
        System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(this.readTimeout));

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(number);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(smsSign);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(smsTemplate);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(parameter);

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId(bussId);

        //hint 此处可能会抛出异常，注意catch
        response = acsClient.getAcsResponse(request);
    }

    public static void main(String[] args){
        SmsKit sender = SmsKit.me().setProduct("Dysmsapi")
                .setDomain("dysmsapi.aliyuncs.com")
                .setAccessKeyId("LTAIu4X4yuqX5vUt")
                .setAccessKeySecret("tsQYkvqSes7An9eGkHeXIUm8GUOtUW")
                .setSmsSign("圆舞曲教育")
                .setSmsTemplate("SMS_150742325")
                .setBussId("SMS000001");

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("code", "123432");

        try {
            sender.sendSms("18580255110", JSON.toJSONString(parameters));
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public boolean sendOK() {
        if (null == this.response)
            return false;

        return SEND_OK.equals(this.response.getCode());
    }

    public String getOutRequestId() {
        if (null == this.response)
            return null;

        return this.response.getRequestId();
    }

    public String getOutResponseId() {
        if (null == this.response)
            return null;

        return this.response.getBizId();
    }

    public String getResponseCode() {
        if (null == this.response)
            return null;

        return this.response.getCode();
    }

    public String getResponseMessage() {
        if (null == this.response)
            return null;

        return this.response.getMessage();
    }
}
