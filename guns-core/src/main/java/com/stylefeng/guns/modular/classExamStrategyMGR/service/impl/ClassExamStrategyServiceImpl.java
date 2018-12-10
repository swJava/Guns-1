package com.stylefeng.guns.modular.classExamStrategyMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.modular.examine.service.IQuestionService;
import com.stylefeng.guns.modular.system.model.ClassExamStrategy;
import com.stylefeng.guns.modular.system.dao.ClassExamStrategyMapper;
import com.stylefeng.guns.modular.classExamStrategyMGR.service.IClassExamStrategyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * <p>
 * 班级入学试题策略 服务实现类
 * </p>
 *
 * @author simple.song
 * @since 2018-12-06
 */
//@Service
public class ClassExamStrategyServiceImpl extends ServiceImpl<ClassExamStrategyMapper, ClassExamStrategy> implements IClassExamStrategyService {

    @Autowired
    private IQuestionService questionService;

    @Autowired(required = false)
    @Qualifier("defaultClassExamStrategy")
    private ClassExamStrategy defaultClassExamStrategy;

    @Override
    public void generateExamine(Student student, com.stylefeng.guns.modular.system.model.Class classInfo, Integer grade, Integer ability) {

        Assert.notNull(classInfo);
        Assert.notNull(student);

        ClassExamStrategy strategy = getExamStrategy(classInfo.getCode());
    }

    private ClassExamStrategy getExamStrategy(String code) {
        Wrapper<ClassExamStrategy> queryWrapper = new EntityWrapper<ClassExamStrategy>();
        queryWrapper.eq("class_code", code);
        ClassExamStrategy strategy = selectOne(queryWrapper);

        if (null == strategy){
            strategy = defaultClassExamStrategy;
        }

        return strategy;
    }
}
