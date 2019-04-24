package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Score;

import java.util.List;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/14 21:44
 * @Version 1.0
 */
public interface ScoreMapper extends BaseMapper<Score> {
    /**
     * 分页查询
     *
     * @param page
     * @param queryParams
     * @return
     */
    List<Score> selectPageList(Page<Score> page, Map<String, Object> queryParams);
}
