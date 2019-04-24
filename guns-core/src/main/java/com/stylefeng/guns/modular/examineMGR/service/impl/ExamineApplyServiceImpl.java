package com.stylefeng.guns.modular.examineMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.examineMGR.service.IExamineApplyService;
import com.stylefeng.guns.modular.examineMGR.service.IExaminePaperService;
import com.stylefeng.guns.modular.system.dao.ExamineApplyMapper;
import com.stylefeng.guns.modular.system.model.ExamineApply;
import com.stylefeng.guns.modular.system.model.ExaminePaper;
import com.stylefeng.guns.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IExaminePaperService examinePaperService;

    @Override
    public List<Map<String, Object>> listPaperUse(String paperCode) {
        if (null == paperCode)
            return new ArrayList<Map<String, Object>>();

        Wrapper<ExamineApply> queryWrapper = new EntityWrapper<ExamineApply>();
        queryWrapper.eq("paper_code", paperCode);

        return selectMaps(queryWrapper);
    }

    @Override
    public void doUse(String paperCode, List<ExamineApply> examineApplyList) {
        if (ToolUtil.isEmpty(paperCode))
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"试卷编码"});

        ExaminePaper paper = examinePaperService.get(paperCode);
        if (null == paper)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"试卷"});

        Wrapper<ExamineApply> queryWrapper = new EntityWrapper<ExamineApply>();
        queryWrapper.eq("paper_code", paperCode);

        if (!delete(queryWrapper))
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_STATE, new String[]{"数据"});

        for(ExamineApply examineApply : examineApplyList){
            examineApply.setPaperCode(paperCode);
            examineApply.setStatus(GenericState.Valid.code);
        }

        insertBatch(examineApplyList);
    }
}
