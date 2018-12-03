package com.stylefeng.guns.modular.classMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.config.DefaultFastjsonConfig;
import com.stylefeng.guns.modular.classMGR.service.IClassExamStrategyService;
import com.stylefeng.guns.modular.examine.service.IQuestionService;
import com.stylefeng.guns.modular.system.dao.ClassExamStrategyMapper;
import com.stylefeng.guns.modular.system.model.ClassExamStrategy;
import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.util.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/30 19:20
 * @Version 1.0
 */
@Service
public class ClassExamStrategyServiceImpl extends ServiceImpl<ClassExamStrategyMapper, ClassExamStrategy> implements IClassExamStrategyService {

    @Autowired
    private IQuestionService questionService;

    @Autowired
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
