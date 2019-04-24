package com.stylefeng.guns.modular.teacherMGR.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Teacher;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author simple.song
 * @since 2018-10-04
 */
public interface TeacherService extends IService<Teacher> {
    /**
     * 创建教师
     *
     * @param teacher
     */
    void create(Teacher teacher);

    /**
     * 禁用教师
     *
     * @param code
     */
    boolean doPause(String code);

    /**
     * 启用教师
     *
     * @param code
     */
    boolean doResume(String code);

    /**
     * 获取老师信息
     *
     * @param code
     * @return
     */
    Teacher get(String code);
}
