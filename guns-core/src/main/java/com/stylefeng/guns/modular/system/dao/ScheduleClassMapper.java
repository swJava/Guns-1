package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.modular.classMGR.transfer.ClassPlan;
import com.stylefeng.guns.modular.system.model.ScheduleClass;

import java.util.List;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/20 0:49
 * @Version 1.0
 */
public interface ScheduleClassMapper extends BaseMapper<ScheduleClass> {
    /**
     *
     * @param queryMap
     * @return
     */
    List<ClassPlan> selectPlanList(Map<String, Object> queryMap);
}
