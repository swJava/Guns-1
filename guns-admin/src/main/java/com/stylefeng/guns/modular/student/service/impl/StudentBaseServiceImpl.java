package com.stylefeng.guns.modular.student.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.StudentBase;
import com.stylefeng.guns.modular.system.dao.StudentBaseMapper;
import com.stylefeng.guns.modular.student.service.IStudentBaseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 学生信息基础表 服务实现类
 * </p>
 *
 * @author simple.song
 * @since 2018-10-01
 */
@Service
public class StudentBaseServiceImpl extends ServiceImpl<StudentBaseMapper, StudentBase> implements IStudentBaseService {

    @Resource
    private StudentBaseMapper studentBaseMapper;

    @Override
    public List<Map<String, Object>> list(Page<StudentBase> page, String condition) {

        return this.studentBaseMapper.list(page, condition);
    }
}
