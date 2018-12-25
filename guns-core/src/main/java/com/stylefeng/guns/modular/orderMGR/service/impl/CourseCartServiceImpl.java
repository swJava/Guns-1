package com.stylefeng.guns.modular.orderMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.classMGR.service.IClassService;
import com.stylefeng.guns.modular.classMGR.service.ICourseService;
import com.stylefeng.guns.modular.education.CourseMethodEnum;
import com.stylefeng.guns.modular.orderMGR.service.ICourseCartService;
import com.stylefeng.guns.modular.orderMGR.service.IOrderService;
import com.stylefeng.guns.modular.system.dao.CourseCartMapper;
import com.stylefeng.guns.modular.system.model.Class;
import com.stylefeng.guns.modular.system.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/15 22:46
 * @Version 1.0
 */
@Service
public class CourseCartServiceImpl extends ServiceImpl<CourseCartMapper, CourseCart> implements ICourseCartService {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IClassService classService;

    @Autowired
    private ICourseService courseService;

    @Override
    public void join(Member member, Student student, com.stylefeng.guns.modular.system.model.Class classInfo) {
        if (null == member)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        if (null == student)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        if (null == classInfo)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        List<CourseCart> existSelected = selectList(new EntityWrapper<CourseCart>()
                .eq("user_name", member.getUserName())
                .eq("student_code", student.getCode())
                .eq("class_code", classInfo.getCode())
                .ne("status", CourseCartStateEnum.Invalid.code)
        );

        if (existSelected.size() > 0)
            throw new ServiceException(MessageConstant.MessageCode.COURSE_SELECTED);

        // 检查班级报名状态
        classService.checkJoinState(classInfo, member, student);

        // 加入选课单
        Map<String, Object> extraParams = new HashMap<String, Object>();
        select(member, student, classInfo, extraParams);
    }

    @Override
    public void remove(Member member, Student student, Class classInfo) {
        if (null == member)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        if (null == student)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        if (null == classInfo)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        CourseCart existSelected = selectOne(new EntityWrapper<CourseCart>()
                        .eq("user_name", member.getUserName())
                        .eq("student_code", student.getCode())
                        .eq("class_code", classInfo.getCode())
                        .eq("status", CourseCartStateEnum.Valid.code)
        );

        if (null == existSelected)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"选课信息"});

        existSelected.setStatus(CourseCartStateEnum.Invalid.code);
        updateById(existSelected);
    }

    @Override
    public CourseCart get(String code) {
        if (null == code)
            return null;

        return selectOne(new EntityWrapper<CourseCart>().eq("code", code));
    }

    private void select(Member member, Student student, Class classInfo, Map<String, Object> extraParams) {
        CourseCart courseCart = new CourseCart();
        Date now = new Date();

        courseCart.setUserName(member.getUserName());
        courseCart.setStudentCode(student.getCode());
        courseCart.setStudent(student.getName());
        courseCart.setJoinDate(now);
        courseCart.setCourseName(classInfo.getCourseName());
        courseCart.setClassCode(classInfo.getCode());
        courseCart.setClassName(classInfo.getName());

        Course course = courseService.get(classInfo.getCourseCode());
        courseCart.setClassMethod(CourseMethodEnum.instanceOf(course.getMethod()).text);

        courseCart.setClassTime(generateTimeDescription(classInfo));
        courseCart.setClassroom(classInfo.getClassRoom());

        courseCart.setTeacher(classInfo.getTeacher());
        courseCart.setAssister(classInfo.getTeacherSecond());

        courseCart.setStatus(CourseCartStateEnum.Valid.code);
        courseCart.setAmount(classInfo.getPrice());
        insert(courseCart);
    }

    private String generateTimeDescription(Class classInfo) {
        return "";
    }
}
