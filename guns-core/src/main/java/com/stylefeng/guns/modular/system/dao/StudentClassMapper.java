package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.modular.system.model.StudentClass;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 学员报班信息表 Mapper 接口
 * </p>
 *
 * @author 罗华
 * @since 2018-12-16
 */
public interface StudentClassMapper extends BaseMapper<StudentClass> {

    /**
     * 班级报班学员列表
     *
     * @param queryMap
     * @return
     */
    List<Student> listSignedStudent(Map<String, Object> queryMap);
}
