package com.stylefeng.guns.modular.examineMGR.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Question;
import com.stylefeng.guns.modular.system.model.QuestionItem;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/30 17:02
 * @Version 1.0
 */
public interface IQuestionService extends IService<Question> {

    Question get(String questionCode);

    /**
     * 删除题目
     *
     * @param questionCode
     */
    void delete(String questionCode);

    /**
     * 创建题目
     *
     * @param question
     * @param items
     */
    void create(Question question, List<QuestionItem> items);

    /**
     * 修改题目
     *
     * @param question
     * @param items
     */
    void update(Question question, List<QuestionItem> items);

    /**
     * 分页查询
     *
     * @param conditionMap
     * @param workingQuestionList
     * @return
     */
    Page<Map<String,Object>> selectMapsPage(Map<String, Object> conditionMap, Collection<String> workingQuestionList);
}
