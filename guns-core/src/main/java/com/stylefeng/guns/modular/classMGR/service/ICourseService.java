package com.stylefeng.guns.modular.classMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Course;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/25 23:51
 * @Version 1.0
 */
public interface ICourseService extends IService<Course> {
    /**
     * 根据课程编码获取信息
     *
     * @param courseCode
     * @return
     */
    Course get(String courseCode);
}
