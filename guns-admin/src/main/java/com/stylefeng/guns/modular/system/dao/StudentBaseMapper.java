package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.StudentBase;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 学生信息基础表 Mapper 接口
 * </p>
 *
 * @author simple.song
 * @since 2018-10-01
 */
public interface StudentBaseMapper extends BaseMapper<StudentBase> {


    /**
     * 获取所有学生列表
     */
    List<Map<String, Object>> list(@Param("page") Page<StudentBase> page, @Param("condition") String condition);

}
