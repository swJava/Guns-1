package com.stylefeng.guns.modular.studentMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Student;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * <p>
 * 学生表 服务类
 * </p>
 *
 * @author simple.song
 * @since 2018-10-07
 */
public interface IStudentService extends IService<Student> {

    /**
     *  获取Student类
     * @return
     */
    Student getOne(Student student);

    /**
     * 为会员添加学员
     *
     * @param userName
     * @param stringObjectMap
     * @return
     */
    Student addStudent(String userName, Map<String, Object> stringObjectMap);

    /**
     * 获取学员头像查看url
     *
     * @param avatorId
     * @return
     */
    String getAvatarViewUrl(Long avatorId);

    /**
     * 更新学员信息
     *
     * @param code
     * @param newStudent
     */
    void updateStudent(String code, Student newStudent);

    Student get(@NotBlank(message = "学员不能为空") String student);
}
