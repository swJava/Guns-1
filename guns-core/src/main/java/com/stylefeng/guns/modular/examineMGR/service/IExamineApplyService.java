package com.stylefeng.guns.modular.examineMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.ExamineApply;

import java.util.List;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/31 15:35
 * @Version 1.0
 */
public interface IExamineApplyService extends IService<ExamineApply> {

    /**
     *
     * @param code
     * @return
     */
    List<Map<String, Object>> listPaperUse(String code);
}
