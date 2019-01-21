package com.stylefeng.guns.modular.examineMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Question;

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
}
