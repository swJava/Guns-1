package com.stylefeng.guns.modular.teacherMGR.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
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

        insert(teacher);
    }
}
