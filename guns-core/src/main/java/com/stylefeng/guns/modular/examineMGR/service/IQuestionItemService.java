package com.stylefeng.guns.modular.examineMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Question;
import com.stylefeng.guns.modular.system.model.QuestionItem;

import java.util.List;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/22 18:34
 * @Version 1.0
 */
public interface IQuestionItemService extends IService<QuestionItem> {
    /**
     * 根据试题找到所有选择项
     *
     * @param code
     * @return
     */
    List<QuestionItem> findAll(String code);

    /**
     * 创建问题答案项
     *
     * @param question
     * @param items
     */
    void create(Question question, List<QuestionItem> items);

    /**
     * 更新问题答案项
     * @param existQuestion
     * @param items
     */
    void update(Question existQuestion, List<QuestionItem> items);

    /**
     * 删除问题答案项
     *
     * 物理删除
     * @param question
     */
    void delete(Question question);
}
