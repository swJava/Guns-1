package com.stylefeng.guns.modular.examineMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.ExaminePaperItem;
import com.stylefeng.guns.modular.system.model.Question;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/22 18:23
 * @Version 1.0
 */
public interface IExaminePaperItemService extends IService<ExaminePaperItem> {
    /**
     * 问题是否在使用
     *
     * @param question
     * @return
     */
    boolean questionOnair(Question question);

    /**
     * 创建
     *
     * @param paperItem
     */
    void create(ExaminePaperItem paperItem);
}
