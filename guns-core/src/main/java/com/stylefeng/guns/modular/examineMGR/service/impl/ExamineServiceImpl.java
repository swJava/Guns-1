package com.stylefeng.guns.modular.examineMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.modular.examineMGR.service.*;
import com.stylefeng.guns.modular.system.model.ExaminePaper;
import com.stylefeng.guns.modular.system.model.ExaminePaperItem;
import com.stylefeng.guns.modular.system.model.Question;
import com.stylefeng.guns.modular.system.model.Student;
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
    public Collection<Question> doBeginExamine(Student student, ExaminePaper paper) {

        // TODO 生成考生答卷

        Wrapper<ExaminePaperItem> questionListQuery = new EntityWrapper<>();
        questionListQuery.eq("paper_code", paper.getCode());
        List<ExaminePaperItem> examinePaperItemList = examinePaperItemService.selectList(questionListQuery);

        if (null == examinePaperItemList || examinePaperItemList.isEmpty()){
            // TODO 阻止业务进行
        }

        Set<Question> questionSet = new HashSet<>();
        for(ExaminePaperItem examinePaperItem : examinePaperItemList){
            questionSet.add(questionService.get(examinePaperItem.getQuestionCode()));
        }

        return questionSet;
    }
}
