package com.stylefeng.guns.modular.classMGR.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.Map;

/**
 * 学生信息包装类
 * @author: simple.song
 * Date: 2018/10/7 Time: 10:55
 */
public class classWrapper extends BaseControllerWarpper{


    public classWrapper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("classRoom", ConstantFactory.me().getClassRoomName((Integer) map.get("classRoomCode")));
        map.put("statusName", ConstantFactory.me().getStatusName((Integer) map.get("status")));
    }
}