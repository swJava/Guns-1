package com.stylefeng.guns.modular.examineMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.examineMGR.QuestionTypeEnum;
import com.stylefeng.guns.modular.examineMGR.service.IQuestionItemService;
import com.stylefeng.guns.modular.system.dao.QuestionItemMapper;
import com.stylefeng.guns.modular.system.model.Question;
import com.stylefeng.guns.modular.system.model.QuestionItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/22 18:34
 * @Version 1.0
 */
@Service
public class QuestionItemServiceImpl extends ServiceImpl<QuestionItemMapper, QuestionItem> implements IQuestionItemService {
    @Override
    public List<QuestionItem> findAll(String code) {
        if (null == code){
            return new ArrayList<QuestionItem>();
        }

        Wrapper<QuestionItem> queryWrapper = new EntityWrapper<>();
        queryWrapper.eq("question_code", code);
        queryWrapper.eq("status", GenericState.Valid.code);

        return selectList(queryWrapper);
    }

    @Override
    public void create(Question question, List<QuestionItem> items) {

        if (null == question)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"题目"});

        QuestionTypeEnum questionType = QuestionTypeEnum.instanceOf(question.getType());

        if (QuestionTypeEnum.UN.equals( questionType ))
            throw new ServiceException(MessageConstant.MessageCode.QUESTION_NO_SUPPORT);

        switch (questionType){
            case SS:
                validateExpectAnswerCount(items, new int[]{1});
                break;
            case MS:
                validateExpectAnswerCount(items, new int[]{2,3,4,5,6,7,8,9,10});
        }

        for(QuestionItem questionItem : items){
            questionItem.setQuestionCode(question.getCode());
            questionItem.setStatus(GenericState.Valid.code);
        }

        insertBatch(items);
    }

    private void validateExpectAnswerCount(List<QuestionItem> items, int[] expectCounts) {

        int expectAnswerCount = 0;
        for(QuestionItem questionItem : items){
            if (0 < questionItem.getExpect())
                expectAnswerCount++;
        }

        boolean validate = false;
        for(int expectCount : expectCounts){
            if (expectCount == expectAnswerCount) {
                validate = true;
                break;
            }
        }

        if (!validate)
            throw new ServiceException(MessageConstant.MessageCode.SYS_DATA_ILLEGAL, new String[]{"请设置正确的答案个数"});

    }


    @Override
    public void update(Question question, List<QuestionItem> items) {

        delete(question);

        create(question, items);

    }

    @Override
    public void delete(Question question) {
        if (null == question)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"题目"});

        Wrapper<QuestionItem> queryWrapper = new EntityWrapper<QuestionItem>();
        queryWrapper.eq("question_code", question.getCode());

        List<QuestionItem> questionItemList = selectList(queryWrapper);
        List<Long> deleteIds = new ArrayList<Long>();
        for(QuestionItem questionItem : questionItemList){
            deleteIds.add(questionItem.getId());
        }
        deleteBatchIds(deleteIds);
    }
}
