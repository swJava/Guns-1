package com.stylefeng.guns.modular.examineMGR.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.examineMGR.service.IExamineAnswerDetailService;
import com.stylefeng.guns.modular.examineMGR.transfer.AnswerDetailDto;
import com.stylefeng.guns.modular.system.dao.ExamineAnswerDetailMapper;
import com.stylefeng.guns.modular.system.model.ExamineAnswerDetail;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/23 16:26
 * @Version 1.0
 */
@Service
public class ExamineAnswerDetailServiceImpl extends ServiceImpl<ExamineAnswerDetailMapper, ExamineAnswerDetail> implements IExamineAnswerDetailService {
    @Override
    public List<AnswerDetailDto> selectDetailList(Wrapper<ExamineAnswerDetail> answerDetailQuery) {
        return null;
    }
}
