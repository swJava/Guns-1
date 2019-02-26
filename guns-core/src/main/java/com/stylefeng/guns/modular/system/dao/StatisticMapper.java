package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Order;
import com.stylefeng.guns.modular.system.model.Question;

import java.util.List;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/2/26 9:36
 * @Version 1.0
 */
public interface StatisticMapper extends BaseMapper<Order> {

    /**
     * 学员报班信息统计
     *
     * @param page
     * @param arguments
     * @return
     */
    List<Map<String,Object>> statisticStudentSign(Page<Map<String, Object>> page, Map<String, Object> arguments);
}
