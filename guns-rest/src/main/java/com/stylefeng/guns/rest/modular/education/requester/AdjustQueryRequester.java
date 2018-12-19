package com.stylefeng.guns.rest.modular.education.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/18 00:30
 * @Version 1.0
 */
@ApiModel(value = "AdjustQueryRequester", description = "调课可调班级查询")
public class AdjustQueryRequester extends SimpleRequester {
    @ApiModelProperty(name = "student", value = "学员编码", required = true, position = 0, example = "XY181220000001")
    @NotBlank(message = "学员编码不能为空")
    private String student;

    @ApiModelProperty(name = "outlineCode", value = "课时编码", required = true, position = 1, example = "KS000001")
    @NotBlank(message = "课时编码不能为空")
    private String outlineCode;

    @ApiModelProperty(name = "classCode", value = "班级编码（当前报名班级）", required = true, position = 2, example = "BJ000001")
    @NotBlank(message = "当前报班信息不能为空")
    private String classCode;

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getOutlineCode() {
        return outlineCode;
    }

    public void setOutlineCode(String outlineCode) {
        this.outlineCode = outlineCode;
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
