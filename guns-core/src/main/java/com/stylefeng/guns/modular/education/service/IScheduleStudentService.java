package com.stylefeng.guns.modular.education.service;

import com.stylefeng.guns.modular.system.model.ScheduleStudent;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/17 9:23
 * @Version 1.0
 */
public interface IScheduleStudentService {
    /**
     * 获取调整后的课程计划
     *
     * @param code
     * @return
     */
    ScheduleStudent getAdjustedSchedule(String preCode);
}
