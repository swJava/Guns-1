package com.stylefeng.guns.modular.examineMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.ClassExamStrategy;
import com.stylefeng.guns.modular.system.model.Student;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/30 19:20
 * @Version 1.0
 */
public interface IClassExamStrategyService extends IService<ClassExamStrategy> {

    void generateExamine(Student student, com.stylefeng.guns.modular.system.model.Class classCode, Integer grade, Integer ability);
}
