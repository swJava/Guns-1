package com.stylefeng.guns.modular.examineMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.examineMGR.service.IExaminePaperService;
import com.stylefeng.guns.modular.system.dao.ExaminePaperMapper;
import com.stylefeng.guns.modular.system.model.Column;
import com.stylefeng.guns.modular.system.model.Content;
import com.stylefeng.guns.modular.system.model.ContentCategory;
import com.stylefeng.guns.modular.system.model.ExaminePaper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/22 18:13
 * @Version 1.0
 */
@Service
public class ExaminePaperServiceImpl extends ServiceImpl<ExaminePaperMapper, ExaminePaper> implements IExaminePaperService {
    @Override
    public ExaminePaper get(String paperCode) {
        if (null == paperCode)
            return null;

        Wrapper<ExaminePaper> queryWrapper = new EntityWrapper<ExaminePaper>();
        queryWrapper.eq("code", paperCode);

        return selectOne(queryWrapper);
    }

    @Override
    public void joinQuestion(String paper, Set<String> questionCodes) {
        if(null == paper)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"栏目"});

    }

    @Override
    public void removeQuestion(String paper, Set<String> questionCodes) {

    }
}
