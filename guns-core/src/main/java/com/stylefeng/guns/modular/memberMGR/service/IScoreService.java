package com.stylefeng.guns.modular.memberMGR.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Score;

import java.util.Map;

/**
 * 查分
 *
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/16 14:29
 * @Version 1.0
 */
public interface IScoreService extends IService<Score> {
    /**
     * 分页查询
     *
     * @param queryParams
     * @return
     */
    Page<Map<String, Object>> selectMapsPage(Map<String, Object> queryParams);

    /**
     * 分页查询
     *
     * @param queryParams
     * @return
     */
    Page<Score> selectPage(Map<String, Object> queryParams, Page<Score> page);
}
