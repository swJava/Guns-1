package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.ExamineAnswer;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 试卷 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-22
 */
public interface ExamineAnswerMapper extends BaseMapper<ExamineAnswer> {

    /**
     * 答卷信息查询
     *
     * @param page
     * @param conditionMap
     * @return
     */
    List<Map<String,Object>> selectPageMix(Page<Map<String, Object>> page, Map<String, Object> conditionMap);
}
