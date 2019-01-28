package com.stylefeng.guns.modular.classMGR.service;

import com.stylefeng.guns.modular.classMGR.transfer.ClassPlan;
import com.stylefeng.guns.modular.system.model.Class;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.model.Student;

import java.util.Collection;
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
    Class get(String code);

    /**
     * 根据班级编码获取班级Map
     *
     * @param code
     * @return
     */
    Map<String,Object> getMap(String code);

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

    /**
     * 查找哪些班级需要进行测试的
     *
     * @param code
     * @return
     */
    List<Class> findClassUsingExaming(Collection<String> paperCodes);

    /**
     * 创建班级
     *
     * @param classInstance
     */
    void createClass(Class classInstance);

    /**
     * 创建班级
     *
     * @param classInstance
     * @param classPlanList
     */
    void createClass(Class classInstance, List<ClassPlan> classPlanList);

    /**
     * 更新班级信息
     *
     * @param classInstance
     */
    void updateClass(Class classInstance);

    /**
     * 删除班级信息
     *
     * 逻辑删
     * @param classCode
     */
    void deleteClass(String classCode);

}
