package com.stylefeng.guns.modular.classRoomMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.classRoomMGR.service.IClassroomService;
import com.stylefeng.guns.modular.system.dao.ClassroomMapper;
import com.stylefeng.guns.modular.system.model.Classroom;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 教室表 服务实现类
 * </p>
 *
 * @author simple.song
 * @since 2018-11-05
 */
@Service
public class ClassroomServiceImpl extends ServiceImpl<ClassroomMapper, Classroom> implements IClassroomService {

    @Override
    public Classroom get(String code) {
        if (null == code)
            return null;

        return selectOne(new EntityWrapper<Classroom>().eq("code", code));
    }

    @Override
    public boolean doPause(String code) {
        if (null == code)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"教室"});

        Classroom classroom = get(code);

        if (null == classroom)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"教室"});

        classroom.setStatus(GenericState.Invalid.code);
        updateById(classroom);

        return true;
    }

    @Override
    public boolean doResume(String code) {
        if (null == code)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"教室"});

        Classroom classroom = get(code);

        if (null == classroom)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"教室"});

        classroom.setStatus(GenericState.Valid.code);
        updateById(classroom);

        return true;
    }
}
