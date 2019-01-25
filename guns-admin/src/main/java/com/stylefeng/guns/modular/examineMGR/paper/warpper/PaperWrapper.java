package com.stylefeng.guns.modular.examineMGR.paper.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/22 16:14
 * @Version 1.0
 */
public class PaperWrapper extends BaseControllerWarpper {

    public PaperWrapper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("statusName", ConstantFactory.me().getStatusName((Integer) map.get("status")));
        map.put("gradesName", ConstantFactory.me().getGradeName(Integer.parseInt((String)map.get("grades"))));
        map.put("subjectName", ConstantFactory.me().getsubjectName(Integer.parseInt((String)map.get("subject"))));
    }
}
