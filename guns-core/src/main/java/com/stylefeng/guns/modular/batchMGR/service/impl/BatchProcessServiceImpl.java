package com.stylefeng.guns.modular.batchMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.batchMGR.service.IBatchProcessService;
import com.stylefeng.guns.modular.system.dao.BatchProcessMapper;
import com.stylefeng.guns.modular.system.model.BatchProcess;
import com.stylefeng.guns.modular.system.model.BatchProcessStatusEnum;
import com.stylefeng.guns.util.CodeKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/18 00:34
 * @Version 1.0
 */
@Service
public class BatchProcessServiceImpl extends ServiceImpl<BatchProcessMapper, BatchProcess> implements IBatchProcessService {
    private static final Logger log = LoggerFactory.getLogger(BatchProcessServiceImpl.class);

    @Override
    public void doCreate(BatchProcess batchProcess) {

        Date now = new Date();
        batchProcess.setCode(CodeKit.generateOrder());
        batchProcess.setCompleteCount(0);
        batchProcess.setStatus(GenericState.Valid.code);
        batchProcess.setImportDate(now);
        batchProcess.setWorkStatus(BatchProcessStatusEnum.Create.code);

        insert(batchProcess);
    }

    @Override
    public void doUpdate(BatchProcess batchProcess) {
        if (null == batchProcess)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"批量任务为空"});

        String batchCode = batchProcess.getCode();
        if (null == batchCode)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"批量任务不存在"});

        BatchProcess existProcess = get(batchCode);
        if (null == existProcess)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"批处理任务"});

        String[] ignoreProperties = new String[]{"id", "code"};
        BeanUtils.copyProperties(batchProcess, existProcess, ignoreProperties);

        updateById(existProcess);
    }

    @Override
    public void delete(String batchCode) {

        if (null == batchCode)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"批次号"});

        BatchProcess process = get(batchCode);

        if (null == process)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"批处理任务"});

        if (process.isProcessing())
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_STATE, new String[]{"任务正在进行"});

        process.setStatus(GenericState.Invalid.code);
        updateById(process);
    }

    private BatchProcess get(String code) {
        if (null == code)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"批次好"});

        return selectOne(new EntityWrapper<BatchProcess>(){
            {
                eq("code", code);
            }
        });
    }
}
