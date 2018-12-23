package com.stylefeng.guns.modular.memberMGR.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.Map;

/**
 * 学生信息包装类
 * @author: simple.song
 * Date: 2018/10/7 Time: 10:55
 */
public class MemberWrapper extends BaseControllerWarpper{


    public MemberWrapper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("genderName", ConstantFactory.me().getSexName((Integer) map.get("gender")));
        map.put("statusName", ConstantFactory.me().getMemberStatusName((Integer) map.get("status")));
    }
}