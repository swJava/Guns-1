package com.stylefeng.guns.modular.examineMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.examineMGR.service.IExamineApplyService;
import com.stylefeng.guns.modular.system.dao.ExamineApplyMapper;
import com.stylefeng.guns.modular.system.model.ExamineApply;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/31 15:35
 * @Version 1.0
 */
@Service
public class ExamineApplyServiceImpl extends ServiceImpl<ExamineApplyMapper, ExamineApply> implements IExamineApplyService {

    @Override
    public List<Map<String, Object>> listPaperUse(String paperCode) {
        if (null == paperCode)
            return new ArrayList<Map<String, Object>>();

        Wrapper<ExamineApply> queryWrapper = new EntityWrapper<ExamineApply>();
        queryWrapper.eq("paper_code", paperCode);

        return selectMaps(queryWrapper);
    }
}
