package com.stylefeng.guns.modular.orderMGR.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.Map;

/**
 * 学生信息包装类
 * @author: simple.song
 * Date: 2018/10/7 Time: 10:55
 */
public class OrderWrapper extends BaseControllerWarpper{


    public OrderWrapper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("payResultName", ConstantFactory.me().getPayResultName(Integer.valueOf( map.get("payResult").toString())));
        map.put("payStatusName", ConstantFactory.me().getPayStatusName(Integer.valueOf(map.get("payStatus").toString())));
        map.put("payMethodName", ConstantFactory.me().getPayMethodName(Integer.valueOf(map.get("payMethod").toString())));
        map.put("statusName", ConstantFactory.me().getStatusName(Integer.valueOf(map.get("status").toString())));
    }
}