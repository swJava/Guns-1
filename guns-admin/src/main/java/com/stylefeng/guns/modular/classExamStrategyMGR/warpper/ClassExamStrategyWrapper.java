package com.stylefeng.guns.modular.classExamStrategyMGR.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

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
        map.put("gradeName", ConstantFactory.me().getGradeName((Integer) map.get("grade")));
        map.put("statusName", ConstantFactory.me().getStatusName(Integer.parseInt( map.get("status").toString())));
        map.put("studentName", ConstantFactory.me().getStudentName(map.get("studentCode").toString()));
        // TODO: 2018/11/16  试卷名称
        map.put("examName", ConstantFactory.me().getGradeName(Integer.parseInt( map.get("examCode").toString())));
    }
}