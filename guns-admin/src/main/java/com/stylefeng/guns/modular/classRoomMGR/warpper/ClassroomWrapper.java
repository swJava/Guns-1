package com.stylefeng.guns.modular.classRoomMGR.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.Map;

/**
 * 学生信息包装类
 * @author: simple.song
 * Date: 2018/10/7 Time: 10:55
 */
public class ClassroomWrapper extends BaseControllerWarpper{


    public ClassroomWrapper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("typeName", ConstantFactory.me().getClassRoomTypeName((Integer) map.get("type")));
        map.put("statusName", ConstantFactory.me().getGenericStateName((Integer) map.get("status")));
    }
}