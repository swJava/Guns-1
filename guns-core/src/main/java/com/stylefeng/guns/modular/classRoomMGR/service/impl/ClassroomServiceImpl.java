package com.stylefeng.guns.modular.classRoomMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.modular.system.model.Classroom;
import com.stylefeng.guns.modular.system.dao.ClassroomMapper;
import com.stylefeng.guns.modular.classRoomMGR.service.IClassroomService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
}
