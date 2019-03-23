package com.stylefeng.guns.modular.batchMGR.controller;

import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.SuccessTip;
import com.stylefeng.guns.core.base.tips.Tip;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.batchMGR.service.IBatchProcessDetailService;
import com.stylefeng.guns.modular.batchMGR.service.IBatchProcessService;
import com.stylefeng.guns.modular.memberMGR.service.IScoreService;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.util.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/18 00:22
 * @Version 1.0
 */
@Controller
@RequestMapping("/batch/process")
@ServerEndpoint("/batch/process/score/websocket")
public class ScoreBatchController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(ScoreBatchController.class);

    @Autowired
    private IScoreService scoreService;

    @Autowired
    private IBatchProcessService batchProcessService;

    @Autowired
    private IBatchProcessDetailService batchProcessDetailService;

    @RequestMapping(value = "/import/score", method = RequestMethod.POST)
    @ResponseBody
    public Object importData(MultipartFile file, BatchProcess batchProcess){
        checkImportFile(file);

        Map<Integer, List<String>> importDataMap = null;
        try {
            importDataMap = ExcelUtil.readData(file.getInputStream());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ServiceException(MessageConstant.MessageCode.BATCH_IMPORT_FAILED, new String[]{"文件解析失败"});
        }

        if (null == importDataMap || importDataMap.isEmpty())
            throw new ServiceException(MessageConstant.MessageCode.BATCH_IMPORT_FAILED, new String[]{"没有导入数据"});

        batchProcess.setService(BatchServiceEnum.Score.code);
        batchProcess.setImportCount(importDataMap.size());
        batchProcess.setStatus(BatchProcessStatusEnum.Create.code);
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
            batchProcess.setStatus(BatchProcessStatusEnum.Reject.code);
            batchProcess.setRemark(message);
            batchProcessService.doUpdate(batchProcess);
            throw new ServiceException(MessageConstant.MessageCode.BATCH_IMPORT_FAILED, new String[]{message});
        }

        batchProcess.setStatus(BatchProcessStatusEnum.Prepare.code);
        batchProcessService.doUpdate(batchProcess);

        Tip result = SUCCESS_TIP;
        ((SuccessTip) result).setData(batchProcess.getCode());

        return result;
    }

    private BatchProcessDetail assembleProcessDetail(String batchCode, Integer line, List<String> lineData) {
        BatchProcessDetail processDetail = new BatchProcessDetail();
        processDetail.setBatchCode(batchCode);
        processDetail.setLine(line);
        processDetail.setData(String.join(",", lineData));
        processDetail.setStatus(GenericState.Valid.code);
        processDetail.setWorkStatus(BatchProcessDetailStatusEnum.Create.code);
        processDetail.setDuration(0);
        processDetail.setImportDate(new Date());

        return processDetail;
    }

    private Score assembleScore(List<String> lineData) {
        Score score = new Score();
        score.setStudentCode(lineData.get(0));
        score.setStudent(lineData.get(1));
        score.setMobileNumber(lineData.get(2));
        score.setExamineName(lineData.get(3));
        score.setRound(lineData.get(4));
        score.setScore(lineData.get(5));
        score.setTotalScore(lineData.get(6));
        score.setRank(lineData.get(7));
        score.setRemark(lineData.get(8));
        score.setClassCode(lineData.get(9));
        return score;
    }

    private void checkImportFile(MultipartFile file) {
        if (null == file) {
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"导入数据"});
        }

        InputStream fileStream = null;

        try {
            fileStream = file.getInputStream();
        }catch(Exception e){}

        if (null == fileStream)
            throw new ServiceException(MessageConstant.MessageCode.SYS_DATA_ILLEGAL, new String[]{"导入数据异常"});


        int version = ExcelUtil.getVersion(fileStream);

        if (version < 7)
            throw new ServiceException(MessageConstant.MessageCode.SYS_DATA_ILLEGAL, new String[]{"导入文件格式不对，请使用高版本"});
    }
}
