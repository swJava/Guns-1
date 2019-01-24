package com.stylefeng.guns.modular.examineMGR.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.examineMGR.transfer.AnswerDetailDto;
import com.stylefeng.guns.modular.system.model.ExamineAnswerDetail;

import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/23 16:25
 * @Version 1.0
 */
public interface IExamineAnswerDetailService extends IService<ExamineAnswerDetail>{
    /**
     * 查询考卷明细
     *
     * @param answerDetailQuery
     * @return
     */
    List<AnswerDetailDto> selectDetailList(Wrapper<ExamineAnswerDetail> answerDetailQuery);
}
