package com.stylefeng.guns.modular.examineMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.modular.examineMGR.service.IExamineAnswerDetailService;
import com.stylefeng.guns.modular.examineMGR.service.IExamineAnswerService;
import com.stylefeng.guns.modular.system.dao.ExamineAnswerMapper;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.util.CodeKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/22 18:48
 * @Version 1.0
 */
@Service
public class ExamineAnswerServiceImpl extends ServiceImpl<ExamineAnswerMapper, ExamineAnswer> implements IExamineAnswerService {

    @Autowired
    private ExamineAnswerMapper examineAnswerMapper;

    @Autowired
    private IExamineAnswerDetailService examineAnswerDetailService;

    @Override
    public ExamineAnswer generatePaper(Student student, ExaminePaper examinePaper) {

        ExamineAnswer answerPaper = new ExamineAnswer();
        Date now = new Date();

        answerPaper.setCode(CodeKit.generateAnswerPaper());
        answerPaper.setPaperCode(examinePaper.getCode());
        answerPaper.setStudentCode(student.getCode());
        answerPaper.setQuota(examinePaper.getCount());
        answerPaper.setTotalScore(examinePaper.getTotalScore());
        answerPaper.setExamTime(examinePaper.getExamTime());
        answerPaper.setStatus(ExamineAnswerStateEnum.Create.code);
        answerPaper.setBeginDate(now);
        answerPaper.setCreateDate(now);

        insert(answerPaper);

        return answerPaper;
    }

    @Override
    public ExamineAnswer get(String code) {
        if (null == code)
            return null;

        return selectOne(new EntityWrapper<ExamineAnswer>().eq("code", code));
    }

    @Override
    public boolean paperOnair(ExaminePaper paper) {
        Wrapper<ExamineAnswer> queryWrapper = new EntityWrapper<ExamineAnswer>();

        queryWrapper.eq("paper_code", paper.getCode());

        return 0 < selectCount(queryWrapper);
    }

    @Override
    public Page<Map<String, Object>> selectMapsPage(Map<String, Object> conditionMap) {
        Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage();

        List<Map<String, Object>> resultMap = examineAnswerMapper.selectPageMix(page, conditionMap);
        page.setRecords(resultMap);
        return page;
    }

    @Override
    public void doAutoCheckPaper(ExamineAnswer examineAnswer) {

        Wrapper<ExamineAnswerDetail> queryWrapper = new EntityWrapper<>();
        queryWrapper.eq("answer_code", examineAnswer.getCode());
        List<ExamineAnswerDetail> examineAnswerDetailList = examineAnswerDetailService.selectList(queryWrapper);

        int getScore = 0;

        for(ExamineAnswerDetail detail : examineAnswerDetailList){
            String expectAnswer = detail.getAnswer();
            String studentAnswer = detail.getStudentAnswer();

            if (expectAnswer.equals(studentAnswer)){
                detail.setStatus(ExamineAnswerDetailStateEnum.Right.code);
                detail.setScore(detail.getTotalScore());
                getScore += detail.getTotalScore();
            }else{
                detail.setStatus(ExamineAnswerDetailStateEnum.Wrong.code);
                detail.setScore(0);
            }
        }

        examineAnswerDetailService.updateBatchById(examineAnswerDetailList);

        ExamineAnswer existExamineAnswer = get(examineAnswer.getCode());
        existExamineAnswer.setStatus(ExamineAnswerStateEnum.Finish.code);
        existExamineAnswer.setTeacher("科萃教育");
        existExamineAnswer.setScore(getScore);

        updateById(existExamineAnswer);
    }
}
