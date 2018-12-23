package com.stylefeng.guns.modular.adjustMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.adjustMGR.service.IAdjustStudentService;
import com.stylefeng.guns.modular.system.dao.AdjustStudentMapper;
import com.stylefeng.guns.modular.system.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 调课/班申请表(学生) 服务实现类
 * </p>
 *
 * @author simple.song
 * @since 2018-11-19
 */
@Service
public class AdjustStudentServiceImpl extends ServiceImpl<AdjustStudentMapper, AdjustStudent> implements IAdjustStudentService {
    private static final Logger log = LoggerFactory.getLogger(AdjustStudentServiceImpl.class);

    @Override
    public void adjustCourse(Member member, Student student, Map<String, Object> fromData, Map<String, Object> destData) {

        com.stylefeng.guns.modular.system.model.Class sourceClassInfo = (com.stylefeng.guns.modular.system.model.Class) fromData.get("sourceClass");
        com.stylefeng.guns.modular.system.model.Class targetClassInfo = (com.stylefeng.guns.modular.system.model.Class) destData.get("targetClass");
        String outlineCode = (String) fromData.get("outlineCode");

        if (hasApproving(student.getCode(), sourceClassInfo.getCode(), targetClassInfo.getCode(), outlineCode, AdjustStudentTypeEnum.Adjust)){
            throw new ServiceException(MessageConstant.MessageCode.ADJUST_COURSE_DUPLICATE);
        }

        AdjustStudent adjustCourseApply = new AdjustStudent();

        adjustCourseApply.setUserName(member.getUserName());
        adjustCourseApply.setStudentCode(student.getCode());
        adjustCourseApply.setType(AdjustStudentTypeEnum.Adjust.code);
        adjustCourseApply.setOutlineCode(outlineCode);
        adjustCourseApply.setSourceClass(sourceClassInfo.getCode());
        adjustCourseApply.setTargetClass(targetClassInfo.getCode());

        adjustCourseApply.setStatus(GenericState.Valid.code);
        adjustCourseApply.setWorkStatus(AdjustStudentApproveStateEnum.Create.code);
        adjustCourseApply.setCreateTime(new Date());

        insert(adjustCourseApply);
    }

    @Override
    public void adjustClass(Member member, Student student, Map<String, Object> fromData, Map<String, Object> destData) {

        com.stylefeng.guns.modular.system.model.Class sourceClassInfo = (com.stylefeng.guns.modular.system.model.Class) fromData.get("sourceClass");
        com.stylefeng.guns.modular.system.model.Class targetClassInfo = (com.stylefeng.guns.modular.system.model.Class) destData.get("targetClass");


        if (hasApproving(student.getCode(), sourceClassInfo.getCode(), targetClassInfo.getCode(), null, AdjustStudentTypeEnum.Change)){
            throw new ServiceException(MessageConstant.MessageCode.CHANGE_CLASS_DUPLICATE);
        }

        AdjustStudent adjustCourseApply = new AdjustStudent();

        adjustCourseApply.setUserName(member.getUserName());
        adjustCourseApply.setStudentCode(student.getCode());
        adjustCourseApply.setType(AdjustStudentTypeEnum.Change.code);
        adjustCourseApply.setSourceClass(sourceClassInfo.getCode());
        adjustCourseApply.setTargetClass(targetClassInfo.getCode());

        adjustCourseApply.setStatus(GenericState.Valid.code);
        adjustCourseApply.setWorkStatus(AdjustStudentApproveStateEnum.Create.code);
        adjustCourseApply.setCreateTime(new Date());

        insert(adjustCourseApply);
    }

    private boolean hasApproving(String student, String sourceClass, String targetClass, String outlineCode, AdjustStudentTypeEnum type) {
        Wrapper<AdjustStudent> queryWrapper = new EntityWrapper<AdjustStudent>();
        queryWrapper.eq("student_code", student);
        queryWrapper.eq("type", type.code);

        if (null != outlineCode)
            queryWrapper.eq("outline_code", outlineCode);

        queryWrapper.eq("source_class", sourceClass);
        queryWrapper.eq("status", GenericState.Valid.code);
        // 只有被拒绝的重复申请才被允许
        queryWrapper.ne("work_status", AdjustStudentApproveStateEnum.Refuse.code);

        return 0 < selectCount(queryWrapper);
    }
}
