package com.stylefeng.guns.task.batch;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.batchMGR.service.IBatchProcessDetailService;
import com.stylefeng.guns.modular.batchMGR.service.IBatchProcessService;
import com.stylefeng.guns.modular.classMGR.service.IClassService;
import com.stylefeng.guns.modular.classMGR.transfer.ClassPlan;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.modular.system.model.Class;
import com.stylefeng.guns.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/28 01:08
 * @Version 1.0
 */
@Component
public class ClassImportTask extends ImportTaskSupport{
    private static final Logger log = LoggerFactory.getLogger(ClassImportTask.class);
    private static final int MIN_COLUMN_SIZE = 17;
    @Autowired
    private IBatchProcessService batchProcessService;

    @Autowired
    private IBatchProcessDetailService batchProcessDetailService;

    @Autowired
    private IClassService classService;

    @Scheduled(fixedDelay = 6000)
    public void handleExamineCheck(){
        log.info("<<< Examine check begin ");
        //
        Wrapper<BatchProcess> queryWrapper = new EntityWrapper<BatchProcess>();
        queryWrapper.eq("status", GenericState.Valid.code);
        queryWrapper.eq("service", BatchServiceEnum.Class.code);
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

            Map<String, Object> classMap = assembleClassData(processDetail.getData());

            List<ClassPlan> classPlanList = (List<ClassPlan>)classMap.get("planList");
            if (null == classPlanList || classPlanList.isEmpty())
                throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"开班计划"});

            try {
                classService.createClass((Class)classMap.get("classInfo"), classPlanList);
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
        log.info(">>> Import class task complete!");
    }

    private Map<String, Object> assembleClassData(String data) {
        if (null == data || StringUtils.isEmpty(data))
            throw new RuntimeException("没有导入数据");

        if (data.indexOf("@_@") == -1 )
            throw new RuntimeException("导入数据无效");

        String[] datas = data.split("@_@");

        String[] requiredCols = datas[0].split(",");
        if (MIN_COLUMN_SIZE > requiredCols.length)
            throw new RuntimeException("缺少必要的导入数据");

        Map<String , Object> resultMap = new HashMap<String, Object>();

        Class classInfo = new Class();
        String[] courseInfo = getMapping(requiredCols, 0);
        classInfo.setCourseCode(courseInfo[0]);
        classInfo.setCourseName(courseInfo[1]);

        classInfo.setName(getString(requiredCols, 1));
        classInfo.setAcademicYear(getInteger(requiredCols, 2));
        classInfo.setCycle(getMappingCode(requiredCols, 3));
        classInfo.setAbility(getMappingCode(requiredCols, 4));

        String[] teacherInfo = getMapping(requiredCols, 5);
        classInfo.setTeacherCode(teacherInfo[0]);
        classInfo.setTeacher(teacherInfo[1]);

        String[] assisterInfo = getMapping(requiredCols, 6);
        classInfo.setTeacherSecondCode(assisterInfo[0]);
        classInfo.setTeacherSecond(assisterInfo[1]);

        classInfo.setQuato(getInteger(requiredCols, 7));

        String[] classroomInfo = getMapping(requiredCols, 8);
        classInfo.setClassRoomCode(classroomInfo[0]);
        classInfo.setClassRoom(classroomInfo[1]);

        classInfo.setSignable(GenericState.Valid.code);
        classInfo.setSignStartDate(getDate(requiredCols, 9));
        classInfo.setSignStartDate(getDate(requiredCols, 10));
        classInfo.setCrossable(getInteger(requiredCols, 11));
        classInfo.setCrossStartDate(getDate(requiredCols, 12));
        classInfo.setCrossEndDate(getDate(requiredCols, 13));
        classInfo.setStudyTimeDesp(getString(requiredCols, 14));

        resultMap.put("classInfo", classInfo);

        if (datas.length > 1 && StringUtils.isNotEmpty(datas[1])){
            List<ClassPlan> classPlanList = assembleClassPlanInfo(datas[1]);
            resultMap.put("planList", classPlanList);
        }

        return resultMap;
    }

    private List<ClassPlan> assembleClassPlanInfo(String classPlanStr) {
        StringTokenizer classPlanToken = new StringTokenizer(classPlanStr, ",");

        StringBuffer codeBuff = new StringBuffer();
        StringBuffer nameBuff = new StringBuffer();

        List<ClassPlan> classPlanList = new ArrayList<ClassPlan>();
        while(classPlanToken.hasMoreTokens()){
            ClassPlan classPlan = new ClassPlan();

            String classPlanInfo = classPlanToken.nextToken();
            if (StringUtils.isNotEmpty(classPlanInfo)){
                if (classPlanInfo.indexOf("~") == -1)
                    continue;

                String[] classPlans = classPlanInfo.split("~");

                Date beginTime = null;
                Date endTime = null;
                try {
                    beginTime = DateUtil.parse(classPlans[0], "yyyy-MM-dd HH:mm:ss");
                    endTime = DateUtil.parse(classPlans[1], "yyyy-MM-dd HH:mm:ss");
                }catch(Exception e){}

                if (null != beginTime)
                    classPlan.setClassTime(DateUtil.format(beginTime, "HHmm"));
                if (null != endTime)
                    classPlan.setEndTime(DateUtil.format(endTime, "HHmm"));

                classPlan.setStudyDate(DateUtils.truncate(beginTime, Calendar.DAY_OF_MONTH));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(classPlan.getStudyDate());
                classPlan.setWeek(calendar.get(Calendar.DAY_OF_WEEK) - 1);
            }

            classPlanList.add(classPlan);
        }

        return classPlanList;
    }
}
