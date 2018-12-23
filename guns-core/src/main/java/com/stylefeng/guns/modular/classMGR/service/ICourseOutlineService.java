package com.stylefeng.guns.modular.classMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Course;
import com.stylefeng.guns.modular.system.model.CourseOutline;

import java.util.List;

/**
 * <p>
 * 课程大纲 服务类
 * </p>
 *
 * @author simple.song
 * @since 2018-11-17
 */
public interface ICourseOutlineService extends IService<CourseOutline> {


    /**
     * 新增课程大纲
     *
     * @param course        课程
     * @param outlineList      课程大纲列表
     */
    void addCourseOutline(Course course,List<CourseOutline> outlineList);

    /**
     * 获取课时信息
     *
     * @param outlineCode
     * @return
     */
    CourseOutline get(String outlineCode);
}
