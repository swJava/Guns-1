package com.stylefeng.guns.rest.modular.education.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by 罗华.
 */
@ApiModel(value = "StudentChangeRequester", description = "学员信息修改")
public class StudentChangeRequester extends SimpleRequester {
    private static final long serialVersionUID = -4079996038794163693L;

    @ApiModelProperty(name = "code", value = "学员编码", required = true, position = 0, example = "XY18110200000001")
    @NotBlank(message = "学员编码不能为空")
    private String code;

    @ApiModelProperty(name = "name", value = "学员姓名", required = true, position = 1, example = "小明")
    @NotBlank(message = "学员名称不能为空")
    private String name;

    @ApiModelProperty(name = "gendar", value = "学员性别", required = true, position = 2, example = "1")
    @NotNull(message = "性别不能为空")
    private Integer gendar;

    @ApiModelProperty(name = "grade", value = "在读年级", required = true, position = 3, example = "4")
    @NotNull(message = "在读年级不能为空")
    private Integer grade;

    @ApiModelProperty(name = "school", value = "在读学校", position = 4, example = "重庆谢家湾小学")
    private String school;

    @ApiModelProperty(name = "targetSchool", value = "目标学校", position = 5, example = "重庆三中")
    private String targetSchool;

    @ApiModelProperty(name = "avatorId", value = "头像", position = 6)
    private Long avatorId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGendar() {
        return gendar;
    }

    public void setGendar(Integer gendar) {
        this.gendar = gendar;
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
