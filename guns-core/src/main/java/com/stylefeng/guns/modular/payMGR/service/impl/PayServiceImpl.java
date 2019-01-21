package com.stylefeng.guns.modular.payMGR.service.impl;

import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.payMGR.MapEntryConvert;
import com.stylefeng.guns.modular.payMGR.PayRequestBuilderFactory;
import com.stylefeng.guns.modular.payMGR.service.IPayRequestService;
import com.stylefeng.guns.modular.payMGR.service.IPayResultService;
import com.stylefeng.guns.modular.payMGR.service.IPayService;
import com.stylefeng.guns.modular.system.model.Order;
import com.stylefeng.guns.modular.system.model.PayMethodEnum;
import com.stylefeng.guns.modular.system.service.IDictService;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/27 15:15
 * @Version 1.0
 */
@Service
public class PayServiceImpl implements IPayService {
    private static final Logger log = LoggerFactory.getLogger(PayServiceImpl.class);

    @Autowired
    private IPayRequestService payRequestService;
    @Autowired
    private IPayResultService payResultService;
    @Autowired
    private IDictService dictService;
    @Autowired(required = false)
    private PayRequestBuilderFactory builderFactory;
    @Value("${application.pay.mock.enable:false}")
    private boolean mockEnable;

    @Override
    public String createPayOrder(Order order) {

        if (null == builderFactory) {
            if (mockEnable) {
                log.warn("no PayRequestBuilderFactory found, use test mode");
                return UUID.randomUUID().toString().replaceAll("-", "");
            }

            throw new ServiceException(MessageConstant.MessageCode.PAY_ORDER_EXCEPTION, new String[0]);
        }

        PayMethodEnum payChannel = PayMethodEnum.instanceOf(order.getPayMethod());

        if (null == payChannel) {
            log.warn("No pay channel supoort");
            throw new ServiceException(MessageConstant.MessageCode.PAY_METHOD_NOT_FOUND, new String[0]);
        }

        String prepayid = null;

        Map<String, String> postResult = new HashMap<String, String>();
        builderFactory.select(payChannel).order(order).post(new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {

//                InputStream is = httpResponse.getEntity().getContent();
//                byte[] buff = new byte[1024];
//                StringBuilder builder = new StringBuilder();
//                while(is.read(buff) > 0){
//                    builder.append(new String(buff));
//                }
//
//                System.out.println("Response ===> " + builder.toString());

                XStream xStream = new XStream(new StaxDriver());
                xStream.alias("xml", Map.class);
                xStream.registerConverter(new MapEntryConvert());
                Map<String, String> response = (Map<String, String>) xStream.fromXML(httpResponse.getEntity().getContent());

                postResult.putAll(response);

                return null;
            }
        });

        String prepayId = null;
        String message = null;
        if ("SUCCESS".equals(postResult.get("return_code"))){
            if ("SUCCESS".equals(postResult.get("result_code"))){
                prepayId = postResult.get("prepay_id");
            }else{
                message = postResult.get("err_code_des");
            }
        }else{
            message = postResult.get("return_msg");
        }

        if (null == prepayId)
            throw new ServiceException(MessageConstant.MessageCode.PAY_ORDER_EXCEPTION, new String[]{message});

        return prepayId;
    }

}
