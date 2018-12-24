package com.stylefeng.guns.modular.education.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Class;
import com.stylefeng.guns.modular.system.model.ScheduleClass;

import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/20 8:20
 * @Version 1.0
 */
public interface IScheduleClassService extends IService<ScheduleClass> {

    void scheduleClass(Class classInstance, Integer studyTimeType, List<Integer> valueList);

    /**
     * 删除排班安排
     *
     * @param code
     */
    void deleteClassSchedule(String code);
}
