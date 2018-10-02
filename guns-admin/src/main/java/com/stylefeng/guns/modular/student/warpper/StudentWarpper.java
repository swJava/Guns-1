package com.stylefeng.guns.modular.student.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.modular.student.common.TypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 学生类的包装
 */
public class StudentWarpper extends BaseControllerWarpper {

    public StudentWarpper(Object list) {
        super(list);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {

        String type = String.valueOf(map.get("type"));
        if(StringUtils.isNotEmpty(type)){
            if(TypeEnum.TYPE_STUDENT.getCode() == Integer.valueOf(type)){
                map.put("typeName",TypeEnum.TYPE_STUDENT.getMsg());
            }
            if(TypeEnum.TYPE_TEACHER.getCode() == Integer.valueOf(type)){
                map.put("typeName",TypeEnum.TYPE_TEACHER.getMsg());
            }

        }
    }
}