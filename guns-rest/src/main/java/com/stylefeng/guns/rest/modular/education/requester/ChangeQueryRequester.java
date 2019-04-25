package com.stylefeng.guns.rest.modular.education.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/19 22:26
 * @Version 1.0
 */
@ApiModel(value = "ChangeQueryRequester", description = "转班可调班级查询")
public class ChangeQueryRequester extends SimpleRequester {
    @ApiModelProperty(name = "student", value = "学员编码", required = true, position = 0, example = "XY181221000001")
    @NotBlank(message = "学员信息不能为空")
    private String student;

    @ApiModelProperty(name = "classCode", value = "班级编码（当前报名班级）", required = true, position = 1, example = "BJ000001")
    @NotBlank(message = "当前报班信息不能为空")
    private String classCode;

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    @Override
    public boolean checkValidate() {
        return false;
    }
}
