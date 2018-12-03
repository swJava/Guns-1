package com.stylefeng.guns.rest.modular.examine.requester;

import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/30 18:43
 * @Version 1.0
 */
@ApiModel(value = "BeginExamineRequester", description = "开始测试信息")
public class BeginExamineRequester extends SimpleRequester {
    @ApiModelProperty(name = "classCode", value = "班级编码", required = true, position = 0, example = "BJ000001")
    @NotBlank(message = "班级信息不能为空")
    @Pattern(regexp = "^BJ\\d{6}$", message = "班级编码非法")
    private String classCode;

    @ApiModelProperty(name = "grade", value = "年级", required = true, position = 1, example = "2")
    @NotNull(message = "年级信息不能为空")
    private Integer grade;

    @ApiModelProperty(name = "ability", value = "班型", required = true, position = 2, example = "4")
    @NotNull(message = "班型信息不能为空")
    private Integer ability;

    @ApiModelProperty(name = "subject", value = "学科", required = true, position = 3, example = "语文")
    @NotNull(message = "学科信息不能为空")
    private Integer subject;

    @ApiModelProperty(name = "member", value = "会员", required = true, position = 4)
    @Valid
    private Member member;

    @ApiModelProperty(name = "ability", value = "班型", required = true, position = 5)
    @Valid
    private Student student;

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getAbility() {
        return ability;
    }

    public void setAbility(Integer ability) {
        this.ability = ability;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean checkValidate() {
        return false;
    }
}
