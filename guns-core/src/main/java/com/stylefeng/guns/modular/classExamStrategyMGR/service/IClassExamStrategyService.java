package com.stylefeng.guns.modular.classExamStrategyMGR.service;

import com.stylefeng.guns.modular.system.model.ClassExamStrategy;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Student;

/**
 * <p>
 * 班级入学试题策略 服务类
 * </p>
 *
 * @author simple.song
 * @since 2018-12-06
 */
public interface IClassExamStrategyService extends IService<ClassExamStrategy> {

    void generateExamine(Student student, com.stylefeng.guns.modular.system.model.Class classCode, Integer grade, Integer ability);

}
