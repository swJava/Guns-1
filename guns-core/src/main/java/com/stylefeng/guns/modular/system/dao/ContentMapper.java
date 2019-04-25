package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Content;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 内容 Mapper 接口
 * </p>
 *
 * @author simple.song
 * @since 2018-10-25
 */
public interface ContentMapper extends BaseMapper<Content> {
    /**
     * 根据查询条件查询结果
     *
     * @param queryMap
     */
    List<Content> selectByColumn(Map<String, Object> queryMap);

    /**
     * 根据查询条件查询结果
     *
     * 只收集简要信息
     * @param queryMap
     */
    List<Content> selectOutlineByColumn(Map<String, Object> queryMap);

    /**
     * 分页查询
     *
     * @param page
     * @param queryMap
     * @return
     */
    List<Map<String, Object>> selectByColumns(Page<Map<String, Object>> page, Map<String, Object> queryMap);
}
