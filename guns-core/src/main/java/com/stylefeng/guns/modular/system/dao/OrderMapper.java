package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Order;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author simple.song
 * @since 2018-10-18
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 订单查询
     *
     * @param queryParams
     * @return
     */
    List<Map<String,Object>> queryForList(Map<String, Object> queryParams);

    /**
     * 分页查询
     *
     * @param page
     * @param arguments
     * @return
     */
    List<Map<String, Object>> selectPageList(Page<Map<String, Object>> page, Map<String, Object> arguments);
}
