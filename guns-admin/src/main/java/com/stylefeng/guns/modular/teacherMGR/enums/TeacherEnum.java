package com.stylefeng.guns.modular.teacherMGR.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 老师类枚举
 *
 * @author: simple.song
 * Date: 2018/10/4 Time: 21:35
 */
public enum TeacherEnum {
    TYPE_JS("L", "讲师"),
    TYPE_FDY("A", "辅导员"),
    TYPE_WPZJ("E", "外聘专家"),;

    private String code;
    private String msg;

    TeacherEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getByCode(String code) {
        if (StringUtils.isNotEmpty(code)) {
            for (TeacherEnum teacherEnum : values()) {
                if (teacherEnum.getCode().equals(code)) {
                    return teacherEnum.getMsg();
                }
            }
        }
        return null;
    }
}