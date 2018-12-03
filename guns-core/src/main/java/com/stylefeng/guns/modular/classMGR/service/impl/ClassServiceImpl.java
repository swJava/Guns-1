package com.stylefeng.guns.modular.classMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.classMGR.service.IClassService;
import com.stylefeng.guns.modular.system.dao.ClassMapper;
import com.stylefeng.guns.modular.system.model.Class;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.util.DateUtil;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 班级 服务实现类
 * </p>
 *
 * @author simple.song
 * @since 2018-10-20
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements IClassService {

    @Autowired
    private ClassMapper classMapper;

    @Override
    public List<Class> queryForList(String userName, Map<String, Object> queryParams) {
        List<Class> resultList = classMapper.queryForList(queryParams);
        return resultList;
    }

    @Override
    public Class get(String code) {
        if (null == code)
            return null;

        return selectOne(new EntityWrapper<Class>().eq("code", code));
    }

    @Override
    public void checkJoinState(Class classInfo, Member member, Student student) {
        Assert.notNull(classInfo);
        Assert.notNull(member);
        Assert.notNull(student);

        Date signEndDate = classInfo.getSignEndDate();
        Date now = new Date();

        if (signEndDate.before(now))
            throw new ServiceException(MessageConstant.MessageCode.COURSE_SELECT_OUTTIME);
    }
}
