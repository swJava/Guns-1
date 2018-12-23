package com.stylefeng.guns.modular.examineMGR.service;

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
     * 查找该学员之前参与的考试
     *
     * @param student
     * @return
     */
    List<ExaminePaper> findUnCompletePaper(Student student);

    /**
     * 查找适合该学员的测试试卷
     *
     * 通过学员所在年级进行匹配
     *
     * @param student
     * @return
     */
    List<ExaminePaper> findExaminePaper(Student student);

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
}
