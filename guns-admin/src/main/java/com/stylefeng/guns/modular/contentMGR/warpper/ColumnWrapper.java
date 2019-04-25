package com.stylefeng.guns.modular.contentMGR.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.Map;

/**
 * 学生信息包装类
 * @author: simple.song
 * Date: 2018/10/7 Time: 10:55
 */
public class ColumnWrapper extends BaseControllerWarpper{


    public ColumnWrapper(Object obj) {
        super(obj);
    }

@Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("statusName", ConstantFactory.me().getStatusName((Integer) map.get("status")));
    }
}