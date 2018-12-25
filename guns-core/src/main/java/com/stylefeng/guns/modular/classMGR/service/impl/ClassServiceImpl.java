package com.stylefeng.guns.modular.classMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.classMGR.service.IClassService;
import com.stylefeng.guns.modular.education.service.IScheduleClassService;
import com.stylefeng.guns.modular.system.dao.ClassMapper;
import com.stylefeng.guns.modular.system.model.Class;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.util.CodeKit;
import org.apache.shiro.util.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 班级 服务实现类
 * </p>
 *
 * @author simple.song
 * @since 2018-10-20
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements IClassService {
    private static final int[] WeekMapping = new int[]{0, 2, 3, 4, 5, 6, 7, 1};
    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private IScheduleClassService scheduleClassService;

    @Override
    public List<Class> queryForList(String userName, Map<String, Object> queryParams) {
        Iterator<String> queryKeyIter = queryParams.keySet().iterator();
        Map<String, Object> arguments = new HashMap<String, Object>();
        List<String> subjectList = new ArrayList<String>();
        arguments.put("subjectList", subjectList);
        List<Integer> cycleList = new ArrayList<Integer>();
        arguments.put("cycleList", cycleList);
        List<Integer> abilityList = new ArrayList<Integer>();
        arguments.put("abilityList", abilityList);
        List<Integer> methodList = new ArrayList<Integer>();
        arguments.put("methodList", methodList);
        List<Integer> weekList = new ArrayList<Integer>();
        arguments.put("weekList", weekList);
        List<Integer> gradeList = new ArrayList<Integer>();
        arguments.put("gradeList", gradeList);

        while(queryKeyIter.hasNext()){
            String key = queryKeyIter.next();

            if ("status".equals(key)){
                arguments.put("status", queryParams.get(key));
            }

            if ("teacherCode".equals(key)){
                arguments.put("teacherCode", queryParams.get(key));
            }

            if ("assisterCode".equals(key)){
                arguments.put("assisterCode", queryParams.get(key));
            }

            if ("classroomCode".equals(key)){
                arguments.put("classroomCode", queryParams.get(key));
            }

            if ("subjects".equals(key)){
                StringTokenizer tokenizer = new StringTokenizer((String)queryParams.get(key), ",");
                while(tokenizer.hasMoreTokens()){
                    subjectList.add(tokenizer.nextToken());
                }
                arguments.put("subjectList", subjectList);
            }

            if ("classCycles".equals(key)){
                StringTokenizer tokenizer = new StringTokenizer((String)queryParams.get(key), ",");
                while(tokenizer.hasMoreTokens()){
                    try {
                        cycleList.add(Integer.parseInt(tokenizer.nextToken()));
                    }catch(Exception e){}
                }
                arguments.put("cycleList", cycleList);
            }

            if ("abilities".equals(key)){
                StringTokenizer tokenizer = new StringTokenizer((String)queryParams.get(key), ",");
                while(tokenizer.hasMoreTokens()){
                    try {
                        abilityList.add(Integer.parseInt(tokenizer.nextToken()));
                    }catch(Exception e){}
                }
                arguments.put("abilityList", abilityList);
            }

            if ("methods".equals(key)){
                StringTokenizer tokenizer = new StringTokenizer((String)queryParams.get(key), ",");
                while(tokenizer.hasMoreTokens()){
                    try {
                        methodList.add(Integer.parseInt(tokenizer.nextToken()));
                    }catch(Exception e){}
                }
                arguments.put("methodList", methodList);
            }

            if ("weekdays".equals(key)){
                StringTokenizer tokenizer = new StringTokenizer((String)queryParams.get(key), ",");
                while(tokenizer.hasMoreTokens()){
                    try {
                        weekList.add(WeekMapping[Integer.parseInt(tokenizer.nextToken())]);
                    }catch(Exception e){}
                }
                arguments.put("weekList", weekList);
            }

            if ("grades".equals(key)){
                StringTokenizer tokenizer = new StringTokenizer((String)queryParams.get(key), ",");
                while(tokenizer.hasMoreTokens()){
                    try {
                        gradeList.add(Integer.parseInt(tokenizer.nextToken()));
                    }catch(Exception e){}
                }
                arguments.put("gradeList", gradeList);
            }
        }
        List<Class> resultList = classMapper.queryForList(arguments);
        return resultList;
    }

    @Override
    public Class get(String code) {
        if (null == code)
            return null;

        return selectOne(new EntityWrapper<Class>().eq("code", code));
    }

    @Override
    public Map<String, Object> getMap(String code) {
        if (null == code)
            return null;

        return selectMap(new EntityWrapper<Class>().eq("code", code));
    }

    @Override
    public void checkJoinState(Class classInfo, Member member, Student student) {
        Assert.notNull(classInfo);
        Assert.notNull(member);
        Assert.notNull(student);

        Date signEndDate = classInfo.getSignEndDate();
        Date now = new Date();

        if (signEndDate.before(now))
            throw new ServiceException(MessageConstant.MessageCode.COURSE_SELECT_OUTTIME);
    }

    @Override
    public List<Class> findClassUsingExaming(Collection<String> paperCodes) {
        if (null == paperCodes || paperCodes.isEmpty())
            return new ArrayList<Class>();

        Wrapper<Class> queryWrapper = new EntityWrapper<Class>();
        queryWrapper.in("examine_paper", paperCodes.toArray());
        queryWrapper.eq("status", GenericState.Valid.code);

        return selectList(queryWrapper);
    }

    @Override
    public void createClass(Class classInstance) {
        classInstance.setCode(CodeKit.generateClass());
        classInstance.setPrice(classInstance.getPrice() * 100);
        insert(classInstance);

        StringTokenizer valueToken = new StringTokenizer(classInstance.getStudyTimeValue(), ",");
        List<Integer> valueList = new ArrayList<>();
        int totalCount = 0;
        while(valueToken.hasMoreTokens()){
            totalCount++;
            try {
                valueList.add(Integer.parseInt(valueToken.nextToken()));
            }catch(Exception e){}
        }

        if (totalCount != valueList.size()){
            throw new ServiceException(MessageConstant.MessageCode.SCHEDULE_CLASS_FAILED);
        }
        scheduleClassService.scheduleClass(classInstance, classInstance.getStudyTimeType(), valueList);
    }

    @Override
    public void updateClass(Class classInstance) {

        scheduleClassService.deleteClassSchedule(classInstance.getCode());

        String[] ignoreProperties = new String[]{"id", "code", "grade", "courseCode", "period", "courseName"};
        Class currClass = get(classInstance.getCode());

        if (null == currClass)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        BeanUtils.copyProperties(classInstance, currClass, ignoreProperties);
        updateById(classInstance);

        StringTokenizer valueToken = new StringTokenizer(classInstance.getStudyTimeValue(), ",");
        List<Integer> valueList = new ArrayList<>();
        int totalCount = 0;
        while(valueToken.hasMoreTokens()){
            totalCount++;
            try {
                valueList.add(Integer.parseInt(valueToken.nextToken()));
            }catch(Exception e){}
        }

        if (totalCount != valueList.size()){
            throw new ServiceException(MessageConstant.MessageCode.SCHEDULE_CLASS_FAILED);
        }
        scheduleClassService.scheduleClass(classInstance, classInstance.getStudyTimeType(), valueList);
    }

    @Override
    public void deleteClass(String classCode) {
        if (null == classCode)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS);

        Class currClass = get(classCode);

        if (null == currClass)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS);

        currClass.setStatus(GenericState.Invalid.code);

        updateById(currClass);
    }
}
