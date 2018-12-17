package com.stylefeng.guns.modular.education.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.education.service.IScheduleStudentService;
import com.stylefeng.guns.modular.system.dao.ScheduleStudentMapper;
import com.stylefeng.guns.modular.system.model.ScheduleStudent;
import com.stylefeng.guns.modular.system.model.ScheduleStudentEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.net.httpserver.AuthFilter;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/17 14:42
 * @Version 1.0
 */
@Service
public class ScheduleStudentServiceImpl extends ServiceImpl<ScheduleStudentMapper, ScheduleStudent> implements IScheduleStudentService {
    private static final Logger log = LoggerFactory.getLogger(ScheduleStudentServiceImpl.class);

    @Override
    public ScheduleStudent getAdjustedSchedule(String planCode) {

        if (null == planCode)
            return null;

        Wrapper<ScheduleStudent> queryMapper = new EntityWrapper<>();
        queryMapper.eq("fcode", planCode);
        queryMapper.eq("status", ScheduleStudentEnum.Valid.code);

        return selectOne(queryMapper);
    }
}
