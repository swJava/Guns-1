package com.stylefeng.guns.rest.modular.student.responser;

import com.stylefeng.guns.modular.system.model.Student;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/2/26 12:09
 * @Version 1.0
 */
public class StudentResponse extends Student {

    private String memberName;

    private String memberMobile;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }
}
