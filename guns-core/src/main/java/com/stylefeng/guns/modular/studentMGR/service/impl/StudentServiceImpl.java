package com.stylefeng.guns.modular.studentMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.studentMGR.service.IStudentService;
import com.stylefeng.guns.modular.system.dao.StudentMapper;
import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.util.CodeKit;
import com.stylefeng.guns.util.PathUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.plugin2.message.Message;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 学生表 服务实现类
 * </p>
 *
 * @author simple.song
 * @since 2018-10-07
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    @Resource
    private StudentMapper studentMapper;

    @Value("${application.attachment.visit-url}")
    private String attachmentVisitURL;

    public Student getOne(Student student){
        return studentMapper.selectOne(student);
    }

    @Override
    public Student addStudent(String userName, Map<String, Object> studentInfo) {

        if (null == userName)
            throw new ServiceException (MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS);

        Student student = buildStudent(studentInfo);
        student.setUserName(userName);

        insert(student);

        return student;
    }

    @Override
    public String getAvatarViewUrl(Long avatorId) {
        if (null == avatorId || avatorId <= 0)
            return null;

        return PathUtil.generate(attachmentVisitURL, String.valueOf(avatorId));
    }

    @Override
    public void updateStudent(String code, Student newStudent) {
        if (null == code)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        Wrapper<Student> queryWrapper = new EntityWrapper<Student>();
        queryWrapper.eq("code", code);
        Student existStudent = selectOne(queryWrapper);

        if (null == existStudent)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        String[] ignoreProperties = new String[]{"id", "code"};
        BeanUtils.copyProperties(newStudent, existStudent, ignoreProperties);

        updateById(existStudent);
    }

    private Student buildStudent(Map<String, Object> studentInfo) {

        if (null == studentInfo || studentInfo.isEmpty())
            return null;

        Student newStudent = new Student();
        try {
            org.apache.commons.beanutils.BeanUtils.populate(newStudent, studentInfo);
        } catch (Exception e) {
            // 任何异常，都作为数据非法操作
            throw new ServiceException(MessageConstant.MessageCode.SYS_DATA_ILLEGAL);
        }

        newStudent.setCode(CodeKit.generateStudent());
        newStudent.setStatus(GenericState.Valid.code);

        long avatarId = (long) studentInfo.getOrDefault("avatarId", 0L);
        if (avatarId > 0) {
            newStudent.setAvatar(PathUtil.generate(attachmentVisitURL, String.valueOf(avatarId)));
        }

        return newStudent;
    }


}
