package com.stylefeng.guns.modular.payMGR.transfer;

import com.stylefeng.guns.modular.system.model.PayMethodEnum;
import org.springframework.util.ReflectionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/27 22:30
 * @Version 1.0
 */
public class WeixinNotifier extends PayNotifier {

    private static final String SUCCESS_RETURN = "SUCCESS";
    private static final String SUCCESS_RESULT = "SUCCESS";

    public WeixinNotifier(){
        this.channel = PayMethodEnum.weixin;
    }

    private String return_code;

    private String return_msg;

    private String appid;

    private String mch_id; // 商户号

    private String sub_mch_id; // 子商户号

    private String device_info;  // 设备号

    private String nonce_str; // 随机字符串

    private String sign; // 签名

    private String result_code;  // 业务结果

    private String err_code; // 错误代码

    private String err_code_des; // 错误代码描述

    private String openid;  // 用户标识

    private String is_subscribe; // 是否关注公众账号

    private String trade_type; // 交易类型

    private String bank_type; // 付款银行

    private Integer total_fee; // 总金额

    private String fee_type; // 货币种类

    private Integer cash_fee; // 现金支付金额

    private String cash_fee_type;  // 现金支付货币类型

    private String coupon_type; // 代金券类型

    private Integer coupon_fee; // 代金券金额

    private Integer coupon_count; // 代金券数量

    private List<String> coupon_ids = new ArrayList<String>(); // 代金券ID

    private List<Integer> coupon_fees = new ArrayList<Integer>(); // 单个代金券支付金额

    private String transaction_id;  // 微信支付订单号

    private String out_trade_no;  // 商户订单号

    private String attach; // 商家数据包

    private String time_end; // 支付完成时间

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

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getSub_mch_id() {
        return sub_mch_id;
    }

    public void setSub_mch_id(String sub_mch_id) {
        this.sub_mch_id = sub_mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public void setTotal_fee(String total_fee) {
        try {
            this.total_fee = Integer.parseInt(total_fee);
        }catch(Exception e){}
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Integer getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(Integer cash_fee) {
        this.cash_fee = cash_fee;
    }

    public void setCash_fee(String cash_fee) {
        try {
            this.cash_fee = Integer.parseInt(cash_fee);
        }catch(Exception e){}
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public String getCoupon_type() {
        return coupon_type;
    }

    public void setCoupon_type(String coupon_type) {
        this.coupon_type = coupon_type;
    }

    public Integer getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(Integer coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public void setCoupon_fee(String coupon_fee) {
        try {
            this.coupon_fee = Integer.parseInt(coupon_fee);
        }catch(Exception e){}
    }

    public Integer getCoupon_count() {
        return coupon_count;
    }

    public void setCoupon_count(Integer coupon_count) {
        this.coupon_count = coupon_count;
    }

    public void setCoupon_count(String coupon_count) {
        try {
            this.coupon_count = Integer.parseInt(coupon_count);
        }catch(Exception e){}
    }

    public List<String> getCoupon_ids() {
        return coupon_ids;
    }

    public void setCoupon_ids(List<String> coupon_ids) {
        this.coupon_ids = coupon_ids;
    }

    private void addCoupon_id(String value) {
        this.coupon_ids.add(value);
    }

    public List<Integer> getCoupon_fees() {
        return coupon_fees;
    }

    public void setCoupon_fees(List<Integer> coupon_fees) {
        this.coupon_fees = coupon_fees;
    }

    public void addCoupon_fee(Integer value){
        this.coupon_fees.add(value);
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public static WeixinNotifier parse(InputStream notifyStream) throws Exception {
        WeixinNotifier notifyRequester = null;

        Document document = null;
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            document = documentBuilder.parse(notifyStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null == document)
            throw new Exception ("报文解析失败");

        NodeList rootList = document.getElementsByTagName("xml");
        NodeList elements = rootList.item(0).getChildNodes();

        notifyRequester = new WeixinNotifier();
        Map<Integer, String> couponIdMap = new HashMap<Integer, String>();
        Map<Integer, Integer> couponFeeMap = new HashMap<Integer, Integer>();

        String couponIdPrefix = "coupon_id_";
        String couponFeePrefix = "coupon_fee_";
        for(int idx = 0; idx < elements.getLength(); idx++){
            Node x = elements.item(idx);
            String name = x.getNodeName();
            String value = x.getTextContent().replaceAll("<!\\[CDATA\\[", "").replaceAll("]]>", "");

            if (name.startsWith(couponIdPrefix)){
                int key = Integer.parseInt(name.substring(couponIdPrefix.length()));
                couponIdMap.put(key, value);
                continue;
        }


            if (name.startsWith(couponFeePrefix)){
                int key = Integer.parseInt(name.substring(couponFeePrefix.length()));
                couponFeeMap.put(key, Integer.parseInt(value));
                continue;
            }

            String setterName = String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1);
            Method method = ReflectionUtils.findMethod(WeixinNotifier.class, "set" + setterName, new Class[]{String.class});
            if (null == method){
                continue;
            }

            method.invoke(notifyRequester, new Object[]{value});
        }

        if (!couponIdMap.isEmpty()){
            Iterator<Integer> keyIterator = couponIdMap.keySet().iterator();
            notifyRequester.addCoupon_id(couponIdMap.get(keyIterator.next()));
        }

        if (!couponFeeMap.isEmpty()){
            Iterator<Integer> keyIterator = couponFeeMap.keySet().iterator();
            notifyRequester.addCoupon_fee(couponFeeMap.get(keyIterator.next()));
        }

        return notifyRequester;
    }

    @Override
    public boolean paySuccess() {
        if (null == this.return_code)
            return false;
        if (! SUCCESS_RETURN.equals(this.return_code) )
            return false;

        if (null == this.result_code)
            return false;
        if (! SUCCESS_RESULT.equals(this.result_code) )
            return false;

        return true;
    }

    @Override
    public String getOrder() {
        return this.out_trade_no;
    }

    @Override
    public String getMessage() {
        if (!SUCCESS_RETURN.equals(this.return_code))
            return this.return_msg;

        if (!SUCCESS_RESULT.equals(this.result_code))
            return "(" + this.err_code + ")" + this.err_code_des;

        return "";
    }
}
