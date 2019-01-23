package com.stylefeng.guns.modular.examineMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.modular.examineMGR.service.IExaminePaperItemService;
import com.stylefeng.guns.modular.system.dao.ExaminePaperItemMapper;
import com.stylefeng.guns.modular.system.model.ExaminePaperItem;
import com.stylefeng.guns.modular.system.model.Question;
import org.springframework.stereotype.Service;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/22 18:24
 * @Version 1.0
 */
@Service
public class ExaminePaperItemServiceImpl extends ServiceImpl<ExaminePaperItemMapper, ExaminePaperItem> implements IExaminePaperItemService {
    @Override
    public boolean questionOnair(Question question) {
        if (null == question)
            return true;

        Wrapper<ExaminePaperItem> queryWrapper = new EntityWrapper<ExaminePaperItem>();

        queryWrapper.eq("question_code", question.getCode());
        queryWrapper.eq("status", GenericState.Valid.code);

        return 0 < selectCount(queryWrapper);
    }

    @Override
    public void create(ExaminePaperItem paperItem) {
        insert(paperItem);
    }
}
