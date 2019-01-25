package com.stylefeng.guns.modular.examineMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.examineMGR.service.*;
import com.stylefeng.guns.modular.system.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/22 17:16
 * @Version 1.0
 */
@Service
public class ExamineServiceImpl implements IExamineService {

    @Autowired
    private IExaminePaperService examinePaperService;

    @Autowired
    private IExamineAnswerService examineAnswerService;

    @Autowired
    private IExamineAnswerDetailService examineAnswerDetailService;

    @Autowired
    private IExaminePaperItemService examinePaperItemService;

    @Autowired
    private IQuestionService questionService;

    @Override
    public List<ExaminePaper> findUnCompletePaper(Student student) {
        return new ArrayList<ExaminePaper>();
    }

    @Override
    public List<ExaminePaper> findExaminePaper(Student student) {
        Wrapper<ExaminePaper> queryWrapper = new EntityWrapper<ExaminePaper>();
        queryWrapper.like("grades", "[" + student.getGrade() + "]");
        return examinePaperService.selectList(queryWrapper);
    }

    @Override
    public ExaminePaper getExaminePaper(String paperCode) {
        if (null == paperCode)
            return null;

        return examinePaperService.get(paperCode);
    }

    @Override
    public Map<String, Collection<Question>> doBeginExamine(Student student, ExaminePaper examinePaper) {

        // 生成答卷
        ExamineAnswer answerPaper = examineAnswerService.generatePaper(student, examinePaper);

        Wrapper<ExaminePaperItem> questionListQuery = new EntityWrapper<>();
        questionListQuery.eq("paper_code", examinePaper.getCode());
        List<ExaminePaperItem> examinePaperItemList = examinePaperItemService.selectList(questionListQuery);

        if (null == examinePaperItemList || examinePaperItemList.isEmpty()){
            // TODO 阻止业务进行
        }

        Set<Question> questionSet = new HashSet<>();
        for(ExaminePaperItem examinePaperItem : examinePaperItemList){
            questionSet.add(questionService.get(examinePaperItem.getQuestionCode()));
        }

        Map<String, Collection<Question>> beginResult = new HashMap<>();
        beginResult.put(answerPaper.getCode(), questionSet);
        return beginResult;
    }

    @Override
    public void doFinishExamine(String code, List<ExamineAnswerDetail> submitItems) {

        ExamineAnswer answerPaper = examineAnswerService.get(code);

        if (null == answerPaper)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS);

        examineAnswerDetailService.insertBatch(submitItems);

        Date now = new Date();
        answerPaper.setStatus(ExamineAnswerStateEnum.Sumit.code);
        answerPaper.setEndDate(now);

        examineAnswerService.updateById(answerPaper);
    }
}
