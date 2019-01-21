package com.stylefeng.guns.modular.examineMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.examineMGR.service.IExaminePaperItemService;
import com.stylefeng.guns.modular.examineMGR.service.IQuestionItemService;
import com.stylefeng.guns.modular.examineMGR.service.IQuestionService;
import com.stylefeng.guns.modular.system.dao.QuestionMapper;
import com.stylefeng.guns.modular.system.model.Question;
import com.stylefeng.guns.modular.system.model.QuestionAutoMarkingEnum;
import com.stylefeng.guns.modular.system.model.QuestionItem;
import com.stylefeng.guns.util.CodeKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/2 10:54
 * @Version 1.0
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Autowired
    private IExaminePaperItemService examinePaperItemService;

    @Autowired
    private IQuestionItemService questionItemService;

    @Override
    public Question get(String code) {
        if (null == code)
            return null;

        Wrapper<Question> queryWrapper = new EntityWrapper<>();

        queryWrapper.eq("code", code);

        return selectOne(queryWrapper);
    }

    @Override
    public void delete(String code) {
        if (null == code)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"题目"});

        Question question = get(code);
        if (null == question)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"题目"});

        if (examinePaperItemService.questionOnair(question))
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_ONAIR, new String[]{"题目"});

        question.setStatus(GenericState.Invalid.code);
        updateById(question);
    }

    @Override
    public void create(Question question, List<QuestionItem> items) {

        question.setCode(CodeKit.generateQuestion());
        question.setStatus(GenericState.Valid.code);
        question.setAutoMarking(QuestionAutoMarkingEnum.Yes.code);
        insert(question);

        questionItemService.create(question, items);
    }
}
