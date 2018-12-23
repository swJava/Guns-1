package com.stylefeng.guns.modular.examineMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.ExamineAnswer;
import com.stylefeng.guns.modular.system.model.ExaminePaper;
import com.stylefeng.guns.modular.system.model.Student;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/22 16:35
 * @Version 1.0
 */
public interface IExamineAnswerService extends IService<ExamineAnswer> {
    /**
     * 创建答卷
     *
     * @param student
     * @param examinePaper
     * @return
     */
    ExamineAnswer generatePaper(Student student, ExaminePaper examinePaper);

    /**
     * 获取答卷
     *
     * @param code
     */
    ExamineAnswer get(String code);
}
