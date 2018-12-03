package com.stylefeng.guns.modular.classMGR.service;

import com.stylefeng.guns.modular.system.model.Class;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.model.Student;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 班级 服务类
 * </p>
 *
 * @author simple.song
 * @since 2018-10-20
 */
public interface IClassService extends IService<Class> {

    List<Class> queryForList(String userName, Map<String, Object> queryParams);

    /**
     * 根据班级编码获取班级
     *
     * @param classCode
     * @return
     */
    Class get(@NotBlank(message = "班级不能为空")@NotBlank String classCode);

    /**
     * 检查班级报名状态
     *
     * @param classInfo
     * @param member
     * @param student
     *
     * @Exception ServiceException
     */
    void checkJoinState(Class classInfo, Member member, Student student);
}
