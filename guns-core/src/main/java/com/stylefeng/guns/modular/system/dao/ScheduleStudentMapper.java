package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.education.transfer.StudentPlan;
import com.stylefeng.guns.modular.system.model.ScheduleStudent;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 学员教学计划 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-17
 */
public interface ScheduleStudentMapper extends BaseMapper<ScheduleStudent> {

    /**
     * 学员课程表
     *
     * @param queryMap
     * @return
     */
    List<StudentPlan> selectPlanList(Map<String, Object> queryMap);
}
