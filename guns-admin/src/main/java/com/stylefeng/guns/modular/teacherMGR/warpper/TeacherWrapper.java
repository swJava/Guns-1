package com.stylefeng.guns.modular.teacherMGR.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

import java.util.Map;

/**
 * @author: simple.song
 * Date: 2018/10/4 Time: 18:32
 */
public class TeacherWrapper extends BaseControllerWarpper {


    public TeacherWrapper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("gradeName", ConstantFactory.me().getGradeName((Integer) map.get("grade")));
        map.put("typeName", ConstantFactory.me().getTeacherTypeName((Integer) map.get("type")));
        map.put("genderName", ConstantFactory.me().getSexName((Integer) map.get("gender")));
        map.put("statusName", ConstantFactory.me().getStatusName((Integer) map.get("status")));
    }
}