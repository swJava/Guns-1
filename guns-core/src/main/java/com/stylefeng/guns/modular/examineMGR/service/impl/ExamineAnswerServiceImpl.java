package com.stylefeng.guns.modular.examineMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.modular.examineMGR.service.IExamineAnswerService;
import com.stylefeng.guns.modular.system.dao.ExamineAnswerMapper;
import com.stylefeng.guns.modular.system.model.ExamineAnswer;
import com.stylefeng.guns.modular.system.model.ExamineAnswerStateEnum;
import com.stylefeng.guns.modular.system.model.ExaminePaper;
import com.stylefeng.guns.modular.system.model.Student;
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

    @Override
    public ExamineAnswer generatePaper(Student student, ExaminePaper examinePaper) {

        ExamineAnswer answerPaper = new ExamineAnswer();
        answerPaper.setCode(CodeKit.generateAnswerPaper());
        answerPaper.setPaperCode(examinePaper.getCode());
        answerPaper.setStudentCode(student.getCode());
        answerPaper.setQuota(examinePaper.getCount());
        answerPaper.setTotalScore(examinePaper.getTotalScore());
        answerPaper.setExamTime(examinePaper.getExamTime());
        answerPaper.setStatus(ExamineAnswerStateEnum.Create.code);
        answerPaper.setCreateDate(new Date());

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
}
