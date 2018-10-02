package com.stylefeng.guns.modular.student.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.StudentBase;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 学生信息基础表 服务类
 * </p>
 *
 * @author simple.song
 * @since 2018-10-01
 */
public interface IStudentBaseService extends IService<StudentBase> {


    /**
     * 获取所有学生列表
     */
    List<Map<String, Object>> list(Page<StudentBase> page, String condition);

}
