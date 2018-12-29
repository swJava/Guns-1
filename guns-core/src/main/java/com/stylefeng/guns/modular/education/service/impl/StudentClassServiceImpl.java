package com.stylefeng.guns.modular.education.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.classMGR.service.IClassService;
import com.stylefeng.guns.modular.education.service.IStudentClassService;
import com.stylefeng.guns.modular.system.dao.StudentClassMapper;
import com.stylefeng.guns.modular.system.model.Class;
import com.stylefeng.guns.modular.system.model.StudentClass;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/28 9:48
 * @Version 1.0
 */
@Service
public class StudentClassServiceImpl extends ServiceImpl<StudentClassMapper, StudentClass> implements IStudentClassService {

    @Autowired
    private IClassService classService;

    @Override
    public void doChange(String studentCode, String sourceClass, String targetClass) {

        Wrapper<StudentClass> queryWrapper = new EntityWrapper<StudentClass>();
        queryWrapper.eq("student_code", studentCode);
        queryWrapper.eq("class_code", sourceClass);
        queryWrapper.eq("status", GenericState.Valid.code);

        StudentClass currClass = selectOne(queryWrapper);

        if (null == currClass)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"报班信息"});

        currClass.setStatus(GenericState.Invalid.code);
        currClass.setRemark("转入班级 [" + sourceClass +"]");

        String[] ignoreProperties = new String[]{"id", "fcode", "pcode", "pcodes"};
        StudentClass newClass = new StudentClass();
        BeanUtils.copyProperties(currClass, newClass, ignoreProperties);

        Class classInfo = classService.get(targetClass);

        newClass.setClassCode(targetClass);
        newClass.setClassName(classInfo.getName());
        newClass.setStatus(GenericState.Valid.code);

        insert(newClass);
    }
}
