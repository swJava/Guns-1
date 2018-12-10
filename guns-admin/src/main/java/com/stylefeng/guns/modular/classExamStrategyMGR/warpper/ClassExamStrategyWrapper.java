package com.stylefeng.guns.modular.classExamStrategyMGR.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.constant.IsMenu;

import java.util.Map;

/**
 * 学生信息包装类
 * @author: simple.song
 * Date: 2018/10/7 Time: 10:55
 */
public class ClassExamStrategyWrapper extends BaseControllerWarpper{


    public ClassExamStrategyWrapper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("classCodeName", ConstantFactory.me().getClassName(map.get("classCode").toString()));
        map.put("autoMarkingName", IsMenu.YES.getMessage());
    }
}