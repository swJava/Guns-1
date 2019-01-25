package com.stylefeng.guns.modular.examineMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.examineMGR.service.IExamineAnswerService;
import com.stylefeng.guns.modular.examineMGR.service.IExaminePaperItemService;
import com.stylefeng.guns.modular.examineMGR.service.IExaminePaperService;
import com.stylefeng.guns.modular.system.dao.ExaminePaperMapper;
import com.stylefeng.guns.modular.system.model.ExaminePaper;
import com.stylefeng.guns.modular.system.model.ExaminePaperItem;
import com.stylefeng.guns.modular.system.model.Question;
import com.stylefeng.guns.util.CodeKit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/22 18:13
 * @Version 1.0
 */
@Service
public class ExaminePaperServiceImpl extends ServiceImpl<ExaminePaperMapper, ExaminePaper> implements IExaminePaperService {

    @Autowired
    private IExaminePaperItemService examinePaperItemService;

    @Autowired
    private IExamineAnswerService examineAnswerService;

    @Override
    public ExaminePaper get(String paperCode) {
        if (null == paperCode)
            return null;

        Wrapper<ExaminePaper> queryWrapper = new EntityWrapper<ExaminePaper>();
        queryWrapper.eq("code", paperCode);

        return selectOne(queryWrapper);
    }

    @Override
    public void create(ExaminePaper paper, Set<ExaminePaperItem> workingQuestionList) {
        if (null == paper)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"试卷"});

        if (null == workingQuestionList || workingQuestionList.isEmpty())
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"试卷题目"});

        Date now = new Date();
        paper.setCode(CodeKit.generatePaper());
        paper.setCreateDate(now);
        paper.setStatus(GenericState.Valid.code);


        int totalScore = 0;
        int totalCount = 0;
        for(ExaminePaperItem paperItem : workingQuestionList){
            totalCount++;
            int score = 0;
            try{
                score = Integer.parseInt(paperItem.getScore());
            }catch(Exception e){}
            totalScore += score;
        }

        paper.setCount(totalCount);
        paper.setTotalScore(totalScore);
        insert(paper);

        for(ExaminePaperItem paperItem : workingQuestionList){
            try {
                paperItem.setPaperCode(paper.getCode());
                examinePaperItemService.create(paperItem);
            }catch(Exception e){
                throw new ServiceException();
            }
        }
    }

    @Override
    public void delete(String code) {
        if (null == code)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"题目"});

        ExaminePaper paper = get(code);
        if (null == paper)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"题目"});

        if (examineAnswerService.paperOnair(paper))
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_ONAIR, new String[]{"试卷"});

        paper.setStatus(GenericState.Invalid.code);
        updateById(paper);
    }

    @Override
    public void update(ExaminePaper paper, Set<ExaminePaperItem> workingQuestionList) {
        if (null == paper || null == paper.getCode())
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"试卷"});

        ExaminePaper existPaper = get(paper.getCode());
        if (null == existPaper)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"试卷"});

        if (examineAnswerService.paperOnair(paper))
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_ONAIR, new String[]{"试卷"});

        Wrapper<ExaminePaperItem> paperItemWrapper = new EntityWrapper<ExaminePaperItem>();
        paperItemWrapper.eq("paper_code", paper.getCode());

        examinePaperItemService.delete(paperItemWrapper);

        String[] ignoreProperties = new String[]{"id", "code", "createDate"};
        BeanUtils.copyProperties(paper, existPaper, ignoreProperties);

        int totalScore = 0;
        int totalCount = 0;
        for(ExaminePaperItem paperItem : workingQuestionList){
            totalCount++;
            int score = 0;
            try{
                score = Integer.parseInt(paperItem.getScore());
            }catch(Exception e){}
            totalScore += score;
        }
        existPaper.setCount(totalCount);
        existPaper.setTotalScore(totalScore);

        updateById(existPaper);

        for(ExaminePaperItem paperItem : workingQuestionList){
            try {
                paperItem.setPaperCode(existPaper.getCode());
                examinePaperItemService.create(paperItem);
            }catch(Exception e){
                throw new ServiceException();
            }
        }
    }
}
