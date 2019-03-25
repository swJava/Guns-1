package com.stylefeng.guns.task.batch;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.modular.batchMGR.service.IBatchProcessDetailService;
import com.stylefeng.guns.modular.batchMGR.service.IBatchProcessService;
import com.stylefeng.guns.modular.memberMGR.service.IScoreService;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.task.education.ExamineCheckTask;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/25 22:00
 * @Version 1.0
 */
@Component
public class ScoreImportTask {
    private static final Logger log = LoggerFactory.getLogger(ScoreImportTask.class);
    private static final int MIN_COLUMN_SIZE = 8;
    @Autowired
    private IBatchProcessService batchProcessService;

    @Autowired
    private IBatchProcessDetailService batchProcessDetailService;

    @Autowired
    private IScoreService scoreService;

    @Scheduled(fixedDelay = 6000)
    public void handleExamineCheck(){
        log.info("<<< Examine check begin ");
        //
        Wrapper<BatchProcess> queryWrapper = new EntityWrapper<BatchProcess>();
        queryWrapper.eq("status", GenericState.Valid.code);
        queryWrapper.eq("work_status", BatchProcessStatusEnum.Prepare.code);

        BatchProcess preparedProcess = batchProcessService.selectOne(queryWrapper);

        if (null == preparedProcess) {
            log.warn("没有需处理的任务");
            return;
        }
        Wrapper<BatchProcessDetail> detailQueryWrapper = new EntityWrapper<BatchProcessDetail>();
        detailQueryWrapper.eq("batch_code", preparedProcess.getCode());
        detailQueryWrapper.eq("status", GenericState.Valid.code);
        detailQueryWrapper.eq("work_status", BatchProcessDetailStatusEnum.Create.code);

        List<BatchProcessDetail> processDetailList = batchProcessDetailService.selectList(detailQueryWrapper);

        if (null == processDetailList || processDetailList.isEmpty()){
            log.warn("批处理任务{}没有需要处理的数据", preparedProcess.getCode());
            preparedProcess.setWorkStatus(BatchProcessStatusEnum.Pass.code);
            batchProcessService.updateById(preparedProcess);
            return;
        }

        preparedProcess.setWorkStatus(BatchProcessStatusEnum.Working.code);
        batchProcessService.updateById(preparedProcess);
        int successCompleteCount = 0;

        for (BatchProcessDetail processDetail : processDetailList){
            Date handleBeginDate  = new Date();
            processDetail.setWorkStatus(BatchProcessDetailStatusEnum.Working.code);
            batchProcessDetailService.updateById(processDetail);

            Score score = assembleScoreData(processDetail.getData());
            try {
                scoreService.insert(score);
                processDetail.setWorkStatus(BatchProcessDetailStatusEnum.Pass.code);
                processDetail.setRemark(BatchProcessDetailStatusEnum.Pass.text);
                successCompleteCount++;
            }catch(Exception e){
                processDetail.setWorkStatus(BatchProcessDetailStatusEnum.Reject.code);
                processDetail.setRemark(e.getMessage());
            }finally{
                Date handleEndDate = new Date();
                processDetail.setDuration(handleEndDate.getTime() - handleBeginDate.getTime());
                processDetail.setCompleteDate(handleEndDate);
            }
            batchProcessDetailService.updateById(processDetail);
        }

        preparedProcess.setWorkStatus(BatchProcessStatusEnum.Pass.code);
        preparedProcess.setCompleteCount(successCompleteCount);
        preparedProcess.setCompleteDate(new Date());
        batchProcessService.updateById(preparedProcess);
        log.info(">>> Import score task complete!");
    }

    private Score assembleScoreData(String data) {
        if (null == data || StringUtils.isEmpty(data))
            throw new RuntimeException("没有导入数据");

        if (data.indexOf("@_@") == -1 )
            throw new RuntimeException("导入数据无效");

        String[] datas = data.split("@_@");

        String[] requiredCols = datas[0].split(",");
        if (MIN_COLUMN_SIZE > requiredCols.length)
            throw new RuntimeException("缺少必要的导入数据");

        Score score = new Score();
        score.setStudentCode(getString(requiredCols, 0));
        score.setStudent(getString(requiredCols, 1));
        score.setMobileNumber(getString(requiredCols, 2));
        score.setExamineName(getString(requiredCols, 3));
        score.setRound(getString(requiredCols, 4));
        score.setScore(getDouble(requiredCols, 5));
        score.setTotalScore(getDouble(requiredCols, 6));
        score.setRank(getInteger(requiredCols, 7));
        score.setRemark(getString(requiredCols, 8));
        score.setImportDate(new Date());

        if (datas.length > 1 && StringUtils.isNotEmpty(datas[1])){
            String[] classInfo = assembleClassInfo(datas[1]);
            score.setClassCodes(classInfo[0]);
            score.setClassNames(classInfo[1]);
        }

        return score;
    }

    private String[] assembleClassInfo(String classInfoStr) {
        StringTokenizer classInfoToken = new StringTokenizer(classInfoStr, ",");

        StringBuffer codeBuff = new StringBuffer();
        StringBuffer nameBuff = new StringBuffer();

        while(classInfoToken.hasMoreTokens()){
            String classInfo = classInfoToken.nextToken();
            if (classInfo.indexOf("=") != -1){
                String[] infos = classInfo.split("=");
                codeBuff.append(infos[0]).append(",");
                nameBuff.append(infos[1]).append(",");
            }
        }

        return new String[]{
                codeBuff.substring(0, codeBuff.length() - 1),
                nameBuff.substring(0, nameBuff.length() - 1)
        };
    }

    private String getString(String[] datas, int index) {
        return index < 0 ? "" : (index >= datas.length ? "" : datas[index]);
    }

    private Double getDouble(String[] datas, int index) {
        String value = getString(datas, index);
        Double result = 0d;
        try{
            result = Double.parseDouble(value);
        }catch(Exception e){}
        return result;
    }

    private Integer getInteger(String[] datas, int index) {
        String value = getString(datas, index);
        Integer result = 0;
        try{
            result = Integer.parseInt(value);
        }catch(Exception e){}
        return result;
    }

    public static void main(String[] args){

        List<String> lineData = new ArrayList<String>();
        lineData.add("1");
        lineData.add("2");
        lineData.add("3");
        lineData.add("4");
        lineData.add("5");
        lineData.add("6");
        lineData.add("7");
        lineData.add("8");

        StringBuffer buff = new StringBuffer();
        buff.append(String.join(",", lineData.subList(0, 8))).append("@_@");
        int index = 1;
        for(String data : lineData.subList(8, lineData.size())){
            buff.append(data);
            if (index % 2 == 0){
                buff.append(",");
            }else{
                buff.append("=");
            }
            index++;
        }

        int lastCharIndex = buff.length() - 1;
        if (buff.lastIndexOf(",") == lastCharIndex || buff.lastIndexOf("=") == lastCharIndex)
            System.out.println(buff.substring(0, buff.length() - 1));
        else
            System.out.println(buff.toString());
    }
}
