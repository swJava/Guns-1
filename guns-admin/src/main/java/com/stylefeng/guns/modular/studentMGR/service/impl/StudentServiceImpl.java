package com.stylefeng.guns.modular.studentMGR.service.impl;

import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.modular.system.dao.StudentMapper;
import com.stylefeng.guns.modular.studentMGR.service.IStudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生表 服务实现类
 * </p>
 *
 * @author simple.song
 * @since 2018-10-07
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
