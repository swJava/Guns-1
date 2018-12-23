package com.stylefeng.guns.modular.examineMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.examineMGR.service.IExaminePaperService;
import com.stylefeng.guns.modular.system.dao.ExaminePaperMapper;
import com.stylefeng.guns.modular.system.model.ExaminePaper;
import org.springframework.stereotype.Service;

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
}
