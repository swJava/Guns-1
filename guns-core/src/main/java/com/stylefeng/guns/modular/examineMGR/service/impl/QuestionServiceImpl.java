package com.stylefeng.guns.modular.examineMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.examineMGR.service.IQuestionService;
import com.stylefeng.guns.modular.system.dao.QuestionMapper;
import com.stylefeng.guns.modular.system.model.Question;
import org.springframework.stereotype.Service;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/2 10:54
 * @Version 1.0
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
    @Override
    public Question get(String code) {
        if (null == code)
            return null;

        Wrapper<Question> queryWrapper = new EntityWrapper<>();

        queryWrapper.eq("code", code);

        return selectOne(queryWrapper);
    }
}
