package com.stylefeng.guns.modular.classMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Course;

import java.util.Map;

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

    /**
     * 获取实体类的Map
     *
     * @param courseCode
     * @return
     */
    Map<String, Object> getMap(String courseCode);

    /**
     * 停用课程
     *
     * @param code
     */
    boolean doPause(String code);

    /**
     * 启用课程
     *
     * @param code
     */
    boolean doResume(String code);

    /**
     * 创建课程
     *
     * @param course
     */
    void create(Course course);

    /**
     * 更新课程
     *
     * @param course
     */
    void update(Course course);
}
