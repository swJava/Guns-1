package com.stylefeng.guns.common.constant.cache;

/**
 * 缓存标识前缀集合,常用在ConstantFactory类中
 *
 * @author fengshuonan
 * @date 2017-04-25 9:37
 */
public interface CacheKey {

    /**
     * 角色名称(多个)
     */
    String ROLES_NAME = "roles_name_";

    /**
     * 角色名称(单个)
     */
    String SINGLE_ROLE_NAME = "single_role_name_";

    /**
     * 角色英文名称
     */
    String SINGLE_ROLE_TIP = "single_role_tip_";

    /**
     * 部门名称
     */
    String DEPT_NAME = "dept_name_";

    /**
     * 字典编码
     */
    String DICT_CODE = "dict_code_";

    /**
     * 字典组
     */
    String DICT_LIST = "dict_list_";

    /**
     * 科目类型
     */
    String SUBJECT_TYPE = "subject_type_";

    /**
     * 通用状态
     */
    String GENERIC_STATE = "generic_state_";

    /**
     * 性别
     */
    String GENDER_TYPE = "gender_type_";

    /**
     * 教师类型
     */
    String TEACHER_TYPE = "teacher_type_";

    /**
     * 教室类型
     */
    String ROOM_TYPE = "room_type_";

    /**
     * 调整类型
     */
    String ADJUST_TYPE = "adjust_type_";

    /**
     * 班次
     */
    String CLASS_ABILITY = "class_ability_";

    /**
     * 学期
     */
    String CLASS_CYCLE = "class_cycle_";

    /**
     * 授课方式
     */
    String COURSE_METHOD = "course_method_";
}
