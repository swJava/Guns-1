package com.stylefeng.guns.modular.batchMGR.controller;

import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.SuccessTip;
import com.stylefeng.guns.core.base.tips.Tip;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.batchMGR.service.IBatchProcessDetailService;
import com.stylefeng.guns.modular.batchMGR.service.IBatchProcessService;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.modular.system.service.IAttachmentService;
import com.stylefeng.guns.util.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 报名
 *
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/18 00:23
 * @Version 1.0
 */
@Controller
@RequestMapping("/batch")
public class SignBatchController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(SignBatchController.class);

    @Autowired
    private IBatchProcessService batchProcessService;

    @Autowired
    private IBatchProcessDetailService batchProcessDetailService;

    @Autowired
    private IAttachmentService attachmentService;

    @RequestMapping(value = "/import/sign", method = RequestMethod.POST)
    @ResponseBody
    public Object importData(BatchProcess batchProcess, String masterName, String masterCode){
        Attachment importAttachment = null;
        File importFile = null;
        List<Attachment> attachmentList = attachmentService.listAttachment(masterName, masterCode);
        if (null != attachmentList || attachmentList.size() > 0) {
            importAttachment = attachmentList.get(0);
            importFile = new File(importAttachment.getPath());
        }
        checkImportFile(importFile);

        Map<Integer, List<String>> importDataMap = null;
        try {
            importDataMap = ExcelUtil.readData(new FileInputStream(importFile));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ServiceException(MessageConstant.MessageCode.BATCH_IMPORT_FAILED, new String[]{"文件解析失败"});
        }

        if (null == importDataMap || importDataMap.isEmpty())
            throw new ServiceException(MessageConstant.MessageCode.BATCH_IMPORT_FAILED, new String[]{"没有导入数据"});

        batchProcess.setService(BatchServiceEnum.Sign.code);
        batchProcess.setImportCount(importDataMap.size());
        batchProcess.setStatus(GenericState.Valid.code);
        batchProcess.setWorkStatus(BatchProcessStatusEnum.Create.code);
        batchProcessService.doCreate(batchProcess);

        List<BatchProcessDetail> processDetailList = new ArrayList<>();
        for (Integer line : importDataMap.keySet()) {
            List<String> lineData = importDataMap.get(line);

            BatchProcessDetail processDetail = assembleProcessDetail(batchProcess.getCode(), line, lineData);

            processDetailList.add(processDetail);
        }

        try{
            batchProcessDetailService.doBatchCreate(processDetailList);
        }catch(Exception e){
            String message = e.getMessage();
            batchProcess.setWorkStatus(BatchProcessStatusEnum.Reject.code);
            batchProcess.setRemark(message);
            batchProcessService.doUpdate(batchProcess);
            throw new ServiceException(MessageConstant.MessageCode.BATCH_IMPORT_FAILED, new String[]{message});
        }

        batchProcess.setWorkStatus(BatchProcessStatusEnum.Prepare.code);
        batchProcessService.doUpdate(batchProcess);

        Tip result = SUCCESS_TIP;
        ((SuccessTip) result).setData(batchProcess.getCode());

        return result;
    }

    private BatchProcessDetail assembleProcessDetail(String batchCode, Integer line, List<String> lineData) {
        BatchProcessDetail processDetail = new BatchProcessDetail();
        processDetail.setBatchCode(batchCode);
        processDetail.setLine(line);

        StringBuffer buff = new StringBuffer();
        buff.append(String.join(",", lineData));

        // 1,2,3,4,5,6,7,8,9@_@10,11,12
        int lastCharIndex = buff.length() - 1;
        if ( buff.lastIndexOf(",") == lastCharIndex )
            processDetail.setData(buff.substring(0, buff.length() - 1));
        else
            processDetail.setData(buff.toString());
        processDetail.setStatus(GenericState.Valid.code);
        processDetail.setWorkStatus(BatchProcessDetailStatusEnum.Create.code);
        processDetail.setDuration(0L);
        processDetail.setImportDate(new Date());

        return processDetail;
    }

    private void checkImportFile(File file) {
        if (null == file) {
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"导入数据"});
        }

        BufferedInputStream fileStream = null;

        try {
            fileStream = new BufferedInputStream(new FileInputStream(file));
        }catch(Exception e){}

        if (null == fileStream)
            throw new ServiceException(MessageConstant.MessageCode.SYS_DATA_ILLEGAL, new String[]{"导入数据异常"});


        int version = ExcelUtil.getVersion(fileStream);

        if (version < 7)
            throw new ServiceException(MessageConstant.MessageCode.SYS_DATA_ILLEGAL, new String[]{"导入文件格式不对，请使用高版本"});
    }
}
