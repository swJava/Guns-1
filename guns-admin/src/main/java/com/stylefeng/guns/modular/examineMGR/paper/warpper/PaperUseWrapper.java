package com.stylefeng.guns.modular.examineMGR.paper.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/31 15:59
 * @Version 1.0
 */
public class PaperUseWrapper extends BaseControllerWarpper {

    public PaperUseWrapper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("gradeName", ConstantFactory.me().getDictsByCode("school_grade", map.get("grade").toString()));
        map.put("cycleName", ConstantFactory.me().getDictsByCode("cycle", map.get("cycle").toString()));
    }
}
