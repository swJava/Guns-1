package com.stylefeng.guns.modular.examineMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.modular.examineMGR.service.IQuestionItemService;
import com.stylefeng.guns.modular.system.dao.QuestionItemMapper;
import com.stylefeng.guns.modular.system.model.QuestionItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/22 18:34
 * @Version 1.0
 */
@Service
public class QuestionItemServiceImpl extends ServiceImpl<QuestionItemMapper, QuestionItem> implements IQuestionItemService {
    @Override
    public List<QuestionItem> findAll(String code) {
        if (null == code){
            return new ArrayList<QuestionItem>();
        }

        Wrapper<QuestionItem> queryWrapper = new EntityWrapper<>();
        queryWrapper.eq("question_code", code);
        queryWrapper.eq("status", GenericState.Valid.code);

        return selectList(queryWrapper);
    }
}
