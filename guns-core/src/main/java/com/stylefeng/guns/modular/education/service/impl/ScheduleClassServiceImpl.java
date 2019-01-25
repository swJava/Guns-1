package com.stylefeng.guns.modular.education.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.classMGR.service.ICourseOutlineService;
import com.stylefeng.guns.modular.classMGR.transfer.ClassPlanDto;
import com.stylefeng.guns.modular.education.service.IScheduleClassService;
import com.stylefeng.guns.modular.system.dao.ScheduleClassMapper;
import com.stylefeng.guns.modular.system.model.Class;
import com.stylefeng.guns.modular.system.model.CourseOutline;
import com.stylefeng.guns.modular.system.model.ScheduleClass;
import com.stylefeng.guns.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/20 17:49
 * @Version 1.0
 */
@Service
public class ScheduleClassServiceImpl extends ServiceImpl<ScheduleClassMapper, ScheduleClass> implements IScheduleClassService {
    @Autowired
    private ICourseOutlineService courseOutlineService;

    @Autowired
    private ScheduleClassMapper scheduleClassMapper;

    @Override
    public void scheduleClass(com.stylefeng.guns.modular.system.model.Class classInstance, Integer studyTimeType, List<Integer> valueList) {

        Wrapper<CourseOutline> outlineQueryWrapper = new EntityWrapper<>();
        outlineQueryWrapper.eq("course_code", classInstance.getCourseCode());
        outlineQueryWrapper.eq("status", GenericState.Valid.code);
        outlineQueryWrapper.orderBy("sort, id");

        List<CourseOutline> outlineList = courseOutlineService.selectList(outlineQueryWrapper);
        switch(studyTimeType){
            case Calendar.DAY_OF_WEEK:
                // 按周处理
                scheduleClassWeek(classInstance, outlineList, valueList);
                break;
        }
    }

    @Override
    public void deleteClassSchedule(String code) {

        Wrapper<ScheduleClass> classScheduleWrapper = new EntityWrapper<>();
        classScheduleWrapper.eq("class_code", code);

        delete(classScheduleWrapper);
    }

    @Override
    public List<ClassPlanDto> selectPlanList(Map<String, Object> queryMap) {
        return scheduleClassMapper.selectPlanList(queryMap);
    }

    private void scheduleClassWeek(Class classInstance, List<CourseOutline> outlineList, List<Integer> valueList) {
        Date beginDate = classInstance.getBeginDate();
        Date endDate = classInstance.getEndDate();
        Date now = new Date();

        if (null == beginDate || null == endDate)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS);

        if (beginDate.before(now) || endDate.before(now)){
            throw new ServiceException(MessageConstant.MessageCode.SYS_DATA_ILLEGAL);
        }

        Date calDate = beginDate;

        List<ScheduleClass> scheduleClassList = new ArrayList<>();
        for(CourseOutline outline : outlineList){

            ScheduleClass scheduleClass = new ScheduleClass();
            scheduleClass.setClassCode(classInstance.getCode());
            scheduleClass.setClassTime(classInstance.getBeginTime());
            scheduleClass.setEndTime(classInstance.getEndTime());
            scheduleClass.setOutline(outline.getOutline());
            scheduleClass.setOutlineCode(outline.getCode());
            scheduleClass.setStatus(GenericState.Valid.code);
            scheduleClass.setSort(outline.getSort());

            while(valueList.size() > 0 && true) {
                Calendar c = Calendar.getInstance();
                c.setTime(calDate);
                int week = c.get(Calendar.DAY_OF_WEEK);
                if (valueList.contains(week)){
                    scheduleClass.setStudyDate(calDate);
                    scheduleClass.setWeek(week);

                    scheduleClassList.add(scheduleClass);
                    calDate = DateUtil.add(calDate, Calendar.DAY_OF_MONTH, 1);
                    break;
                }

                calDate = DateUtil.add(calDate, Calendar.DAY_OF_MONTH, 1);
            }
        }

        insertBatch(scheduleClassList);
    }
}
