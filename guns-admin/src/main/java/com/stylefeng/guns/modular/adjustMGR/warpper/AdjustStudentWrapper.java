package com.stylefeng.guns.modular.adjustMGR.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.Map;

/**
 * 学生信息包装类
 * @author: simple.song
 * Date: 2018/10/7 Time: 10:55
 */
public class AdjustStudentWrapper extends BaseControllerWarpper{


    public AdjustStudentWrapper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("className", ConstantFactory.me().getClassName((String) map.get("classCode")));
        map.put("targetName", ConstantFactory.me().getClassName((String) map.get("target")));
        map.put("studentName", ConstantFactory.me().getStudentName((String) map.get("studentCode")));
        map.put("typeName", ConstantFactory.me().getAdjustTypeName((Integer) map.get("type")));
        map.put("statusName", ConstantFactory.me().getStatusName(Integer.parseInt( map.get("status").toString())));
        map.put("workStatusName", ConstantFactory.me().getAdjustTypeName((Integer) map.get("workStatus")));
    }
}