package com.stylefeng.guns.modular.classMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.classMGR.service.ICourseService;
import com.stylefeng.guns.modular.system.dao.CourseMapper;
import com.stylefeng.guns.modular.system.model.Course;
import org.springframework.stereotype.Service;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/25 23:52
 * @Version 1.0
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {
    @Override
    public Course get(String code) {
        return selectOne(new EntityWrapper<Course>().eq("code", code));
    }
}
