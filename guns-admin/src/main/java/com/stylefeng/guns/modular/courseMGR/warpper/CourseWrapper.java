package com.stylefeng.guns.modular.courseMGR.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.Map;
import java.util.Optional;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/21 15:22
 * @Version 1.0
 */
public class CourseWrapper extends BaseControllerWarpper {

    public CourseWrapper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("statusName", ConstantFactory.me().getGenericStateName(Integer.parseInt( map.get("status").toString())));
        map.put("methodName", ConstantFactory.me().getCourseMethodname(Integer.parseInt(map.get("method").toString())));
        map.put("gradeName", ConstantFactory.me().getDictsByCode("school_grade", map.get("grade").toString()));
        map.put("subjectName", ConstantFactory.me().getDictsByCode("subject_type", map.get("subject").toString()));
    }
}
