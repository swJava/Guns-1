package com.stylefeng.guns.modular.classMGR.service;

import com.stylefeng.guns.modular.system.model.CourseOutline;
import com.baomidou.mybatisplus.service.IService;

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
     * @param classCode         班级编码
     * @param courseCode        课程编码
     * @param courseValues      课程大纲列表str
     */
    void addCourseOutline(String classCode,String courseCode,String courseValues);

}
