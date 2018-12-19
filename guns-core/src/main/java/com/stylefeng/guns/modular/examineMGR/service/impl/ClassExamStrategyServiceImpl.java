package com.stylefeng.guns.modular.examineMGR.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.examineMGR.service.IClassExamStrategyService;
import com.stylefeng.guns.modular.system.dao.ClassExamStrategyMapper;
import com.stylefeng.guns.modular.system.model.*;
import org.springframework.stereotype.Service;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/18 14:25
 * @Version 1.0
 */
@Service
public class ClassExamStrategyServiceImpl extends ServiceImpl<ClassExamStrategyMapper, ClassExamStrategy> implements IClassExamStrategyService {
    @Override
    public void generateExamine(Student student, com.stylefeng.guns.modular.system.model.Class classCode, Integer grade, Integer ability) {

    }
}
