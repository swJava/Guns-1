package com.stylefeng.guns.modular.examineMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.ExaminePaper;
import com.stylefeng.guns.modular.system.model.ExaminePaperItem;

import java.util.Set;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/22 18:13
 * @Version 1.0
 */
public interface IExaminePaperService extends IService<ExaminePaper> {
    /**
     * 获取试卷
     *
     * @param paperCode
     * @return
     */
    ExaminePaper get(String paperCode);
    /**
     * 添加试卷
     *  @param paper
     * @param workingQuestionList
     */
    void create(ExaminePaper paper, Set<ExaminePaperItem> workingQuestionList);

    /**
     * 删除试卷
     *
     * @param code
     */
    void delete(String code);

    /**
     * 更新试卷
     *
     * @param paper
     * @param workingQuestionList
     */
    void update(ExaminePaper paper, Set<ExaminePaperItem> workingQuestionList);
}
