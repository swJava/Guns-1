package com.stylefeng.guns.modular.classMGR.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.Map;

/**
 * 学生信息包装类
 * @author: simple.song
 * Date: 2018/10/7 Time: 10:55
 */
public class ClassWrapper extends BaseControllerWarpper{


    public ClassWrapper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("classRoom", ConstantFactory.me().getClassRoomName(Integer.parseInt( map.get("classRoomCode").toString())));
        map.put("statusName", ConstantFactory.me().getStatusName(Integer.parseInt( map.get("status").toString())));
        map.put("studyTimeTypeName", ConstantFactory.me().getStatusName(Integer.parseInt( map.get("studyTimeType").toString())));
    }
}