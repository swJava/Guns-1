package com.stylefeng.guns.modular.examineMGR.service;

import com.stylefeng.guns.modular.system.model.ExamineAnswerDetail;
import com.stylefeng.guns.modular.system.model.ExaminePaper;
import com.stylefeng.guns.modular.system.model.Question;
import com.stylefeng.guns.modular.system.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/22 16:39
 * @Version 1.0
 */
public interface IExamineService {
    /**
     * 查找适合的测试试卷
     *
     * @param queryParams
     * @return
     */
    ExaminePaper findExaminePaper(Map<String, Object> queryParams);

    /**
     * 获取试卷
     *
     * @param paperCode
     * @return
     */
    ExaminePaper getExaminePaper(String paperCode);

    /**
     * 开始测试
     *
     * @param student
     * @param paper
     * @return
     */
    Map<String, Collection<Question>> doBeginExamine(Student student, ExaminePaper paper);

    /**
     *  @param code
     * @param submitItems
     */
    void doFinishExamine(String code, List<ExamineAnswerDetail> submitItems);

    /**
     * 查找学员试卷
     *
     * @param student
     * @return
     */
    Collection<Map<String, Object>> findExamineAnswerPaperList(String student);
}
