package com.stylefeng.guns.modular.education.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.modular.system.model.StudentClass;

import java.util.List;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/28 9:47
 * @Version 1.0
 */
public interface IStudentClassService extends IService<StudentClass> {
    /**
     * 转班
     * 
     * @param studentCode
     * @param sourceClass
     * @param targetClass
     */
    void doChange(String studentCode, String sourceClass, String targetClass);

    /**
     * 班级报班学员列表
     *
     * @param queryMap
     * @return
     */
    List<Student> listSignedStudent(Map<String, Object> queryMap);
}
