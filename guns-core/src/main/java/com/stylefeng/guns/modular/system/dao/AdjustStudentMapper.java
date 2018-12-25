package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.AdjustStudent;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 调课/班申请表(学生) Mapper 接口
 * </p>
 *
 * @author simple.song
 * @since 2018-11-19
 */
public interface AdjustStudentMapper extends BaseMapper<AdjustStudent> {
    /**
     * 查询调课/转班申请列表
     *
     * @param page
     * @param queryMap
     * @return
     */
    List<Map<String,Object>> selectApplyMapsPage(Page<AdjustStudent> page, Map<String, Object> queryMap);
}
