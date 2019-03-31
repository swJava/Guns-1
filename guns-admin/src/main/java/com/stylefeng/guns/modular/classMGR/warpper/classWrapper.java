package com.stylefeng.guns.modular.classMGR.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.modular.classMGR.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

/**
 * 学生信息包装类
 * @author: simple.song
 * Date: 2018/10/7 Time: 10:55
 */
public class ClassWrapper extends BaseControllerWarpper{
    private static final BigDecimal YUAN_FEN = new BigDecimal("100");

    public ClassWrapper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("statusName", ConstantFactory.me().getStatusName(Integer.parseInt(map.get("status").toString())));
        map.put("gradeName", ConstantFactory.me().getDictsByCode("school_grade", map.get("grade").toString()));
        map.put("cycleName", ConstantFactory.me().getDictsByCode("cycle", map.get("cycle").toString()));

        int quato = Integer.parseInt(map.get("quato").toString());
        if (map.containsKey("signQuato")) {
            int signQuato = Integer.parseInt(map.get("signQuato").toString());
            map.put("remainderQuato", quato - signQuato);
        }
        Optional.ofNullable(map.get("price")).ifPresent(Price->map.put("price", new BigDecimal(Price.toString()).divide(YUAN_FEN).setScale(2, BigDecimal.ROUND_DOWN).toString()));
    }
}