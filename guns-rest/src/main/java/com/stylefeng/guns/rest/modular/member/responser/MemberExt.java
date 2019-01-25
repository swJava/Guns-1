package com.stylefeng.guns.rest.modular.member.responser;

import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.model.Student;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

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
    @ApiModelProperty(name = "avatar", value = "用户头像url")
    private String avatar;
    /**
     * 关联学员信息
     */
    @ApiModelProperty(name = "students", value = "关联学员列表")
    private List<Student> students = new ArrayList<Student>();

    public static MemberExt me(Member member) {
        MemberExt memberExt = new MemberExt();
        String[] ignoreProperties = new String[] {"id", "password"};
        BeanUtils.copyProperties(member, memberExt, ignoreProperties);

        return memberExt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public MemberExt assembleStudent(List<Student> studentList) {
        if (null == studentList || studentList.isEmpty())
            return this;

        this.students.addAll(studentList);

        return this;
    }
}
