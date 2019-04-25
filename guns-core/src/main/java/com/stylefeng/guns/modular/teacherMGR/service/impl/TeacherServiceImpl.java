package com.stylefeng.guns.modular.teacherMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.system.model.Teacher;
import com.stylefeng.guns.modular.system.dao.TeacherMapper;
import com.stylefeng.guns.modular.teacherMGR.service.TeacherService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.util.CodeKit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author simple.song
 * @since 2018-10-04
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {


    @Override
    public void create(Teacher teacher) {
        teacher.setCode(CodeKit.generateTeacher());
        teacher.setStatus(GenericState.Valid.code);
        insert(teacher);
    }

    @Override
    public boolean doPause(String code) {
        if (null == code)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"教师编码"});

        Teacher teacher = get(code);

        if (null == teacher)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"教师"});

        teacher.setStatus(GenericState.Invalid.code);
        updateById(teacher);

        return true;
    }

    @Override
    public boolean doResume(String code) {
        if (null == code)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"教师编码"});

        Teacher teacher = get(code);

        if (null == teacher)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"教师"});

        teacher.setStatus(GenericState.Valid.code);
        updateById(teacher);

        return true;
    }

    @Override
    public Teacher get(String code) {
        if (null == code)
            return null;

        Wrapper<Teacher> queryWrapper = new EntityWrapper<Teacher>();
        queryWrapper.eq("code", code);

        return selectOne(queryWrapper);
    }
}
