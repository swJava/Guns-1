package com.stylefeng.guns.modular.education.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.ScheduleStudent;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/17 9:23
 * @Version 1.0
 */
public interface IScheduleStudentService extends IService<ScheduleStudent>{
    /**
     * 获取调整后的课程计划
     *
     * @param preCode
     * @return
     */
    ScheduleStudent getAdjustedSchedule(String preCode);

    /**
     * 调课
     * ˙
     * @param studentCode
     * @param outlineCode
     * @param sourceClass
     * @param targetClass
     */
    void doAdjust(String studentCode, String outlineCode, String sourceClass, String targetClass);

    /**
     * 转班
     *
     * @param studentCode
     * @param sourceClass
     * @param targetClass
     */
    void doChange(String studentCode, String sourceClass, String targetClass);
}
