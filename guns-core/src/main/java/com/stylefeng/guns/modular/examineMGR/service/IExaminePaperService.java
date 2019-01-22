package com.stylefeng.guns.modular.examineMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.ExaminePaper;

import java.util.Set;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/22 18:13
 * @Version 1.0
 */
public interface IExaminePaperService extends IService<ExaminePaper> {

    ExaminePaper get(String paperCode);

    /**
     * 试卷加题
     *
     * @param paper
     * @param questionCodes
     */
    void joinQuestion(String paper, Set<String> questionCodes);

    /**
     * 试卷减题
     *
     * @param paper
     * @param questionCodes
     */
    void removeQuestion(String paper, Set<String> questionCodes);
}
