package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Question;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 试题库 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-22
 */
public interface QuestionMapper extends BaseMapper<Question> {
    /**
     * 分页查询
     *
     * @param page
     * @param conditionMap
     * @return
     */
    List<Map<String,Object>> selectPageByPaper(Page<Map<String, Object>> page, Map<String, Object> conditionMap);
}
