package com.stylefeng.guns.rest.modular.member.responser;

import com.stylefeng.guns.modular.system.model.Member;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/19 14:22
 * @Version 1.0
 */
public class MemberExt extends Member {

    /**
     * 用户头像
     */
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
