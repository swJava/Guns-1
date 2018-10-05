package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Teacher;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author simple.song
 * @since 2018-10-04
 */
public interface TeacherMapper extends BaseMapper<Teacher> {

    List<Map<String, Object>> selectTeachers(@Param("page")Page page, @Param("name") String name);

}
