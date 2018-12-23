package com.stylefeng.guns.modular.examineMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.QuestionItem;

import java.util.List;

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
}
