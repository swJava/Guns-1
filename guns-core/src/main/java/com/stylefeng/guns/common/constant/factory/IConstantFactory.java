package com.stylefeng.guns.common.constant.factory;

import com.stylefeng.guns.modular.system.model.Dict;

import java.util.List;

/**
 * 常量生产工厂的接口
 *
 * @author fengshuonan
 * @date 2017-06-14 21:12
 */
public interface IConstantFactory {

    /**
     * 根据用户id获取用户名称
     *
     * @author stylefeng
     * @Date 2017/5/9 23:41
     */
    String getUserNameById(Integer userId);

    /**
     * 根据用户id获取用户账号
     *
     * @author stylefeng
     * @date 2017年5月16日21:55:371
     */
    String getUserAccountById(Integer userId);

    /**
     * 通过角色ids获取角色名称
     */
    String getRoleName(String roleIds);

    /**
     * 通过角色id获取角色名称
     */
    String getSingleRoleName(Integer roleId);

    /**
     * 通过角色id获取角色英文名称
     */
    String getSingleRoleTip(Integer roleId);

    /**
     * 获取部门名称
     */
    String getDeptName(Integer deptId);

    /**
     * 获取菜单的名称们(多个)
     */
    String getMenuNames(String menuIds);

    /**
     * 获取菜单名称
     */
    String getMenuName(Long menuId);

    /**
     * 获取菜单名称通过编号
     */
    String getMenuNameByCode(String code);

    /**
     * 获取字典名称
     */
    String getDictName(Integer dictId);

    /**
     * 获取通知标题
     */
    String getNoticeTitle(Integer dictId);

    /**
     * 根据字典名称和字典中的值获取对应的名称
     */
    String getDictsByName(String name, Integer val);

    /**
     * 获取性别名称
     */
    String getTeacherTypeName(Integer teacherType);

    /**
     * 获取授课年级名称
     */
    String getGradeName(Integer grade);

    /**
     * 获取性别名称
     */
    String getSexName(Integer sex);

    /**
     * 获取用户登录状态
     */
    String getStatusName(Integer status);

    /**
     * 获取菜单状态
     */
    String getMenuStatusName(Integer status);

    /**
     * 查询字典
     */
    List<Dict> findInDict(Integer id);

    /**
     * 获取被缓存的对象(用户删除业务)
     */
    String getCacheObject(String para);

    /**
     * 获取子部门id
     */
    List<Integer> getSubDeptId(Integer deptid);

    /**
     * 获取所有父部门id
     */
    List<Integer> getParentDeptIds(Integer deptid);

    /**
     * 获取支付状态
     */
    String getPayStatusName(Integer payStatus);

    /**
     * 支付方式
     */
    String getPayMethodName(Integer payMethod);

    /**
     * 支付结果
     */
    String getPayResultName(Integer payResult);

    /**
     * 获取教室名称
     */
    String getClassRoomName(Integer classCode);

    /**
     * 获取校区地址
     */
    String getSchoolAdressName(Integer classCode);

    /**
     * 开课类型
     */
    String getStudyTimeTypeName(Integer studyTimeType);

    /**
     * 资讯类型
     * @param type
     * @return
     */
    String getContentTypeName(Integer type);

    /**
     * 试题类型
     * @param type
     * @return
     */
    String getQuestionTypeName(Integer type);

    /**
     * 学科类型
     * @param subject
     * @return
     */
    String getsubjectName(Integer subject);


    /**
     * 获取教室类型
     * @param type
     * @return
     */
    String getClassRoomTypeName(Integer type);
}
