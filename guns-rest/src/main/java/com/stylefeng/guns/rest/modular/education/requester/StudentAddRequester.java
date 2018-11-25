package com.stylefeng.guns.rest.modular.education.requester;

import com.stylefeng.guns.modular.system.model.Attachment;
import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by 罗华.
 */
@ApiModel(value = "StudentAddRequester", description = "添加学员")
public class StudentAddRequester extends SimpleRequester {

    private static final long serialVersionUID = -1976690924147728427L;
    @ApiModelProperty(name = "userName", value = "用户名", required = true, position = 0, example = "18580255110")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(name = "name", value = "学员姓名", required = true, position = 1, example = "小明")
    @NotBlank(message = "学员姓名不能为空")
    @Size(min = 1, max = 8, message = "用户名长度只能是1 - 8 位字符")
    private String name;

    @ApiModelProperty(name = "gender", value = "学员性别", required = true, position = 2, example = "1")
    @NotNull(message = "学员性别不能为空")
    private Integer gender;

    @ApiModelProperty(name = "grade", value = "在读年级", required = true, position = 3, example = "4")
    @NotNull(message = "在读年级不能为空")
    private Integer grade;

    @ApiModelProperty(name = "school", value = "在读学校", position = 4, example = "重庆谢家湾小学")
    @Size(max = 64, message = "在读学校名称过长")
    private String school;

    @ApiModelProperty(name = "targetSchool", value = "目标学校", position = 5, example = "重庆三中")
    @Size(max = 64, message = "目标学校名称过长")
    private String targetSchool;

    @ApiModelProperty(name = "avator", value = "头像", position = 6)
    private Long avatorId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTargetSchool() {
        return targetSchool;
    }

    public void setTargetSchool(String targetSchool) {
        this.targetSchool = targetSchool;
    }

    public Long getAvatorId() {
        return avatorId;
    }

    public void setAvatorId(Long avatorId) {
        this.avatorId = avatorId;
    }

    @Override
    public boolean checkValidate() {
        return false;
    }
}
