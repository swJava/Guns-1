package com.stylefeng.guns.rest.modular.education.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/17 8:19
 * @Version 1.0
 */
@ApiModel(value = "OutlineListQueryRequester", description = "课时信息查询")
public class OutlineListQueryRequester extends SimpleRequester {
    @ApiModelProperty(name = "classCode", value = "班级编码", required = true, position = 0, example = "BJ000001")
    @NotBlank(message = "班级编码不能为空")
    private String classCode;

    @ApiModelProperty(name = "student", value = "学员编码", required = false, position = 1, example = "XY181131000001")
    private String student;

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

    public boolean notDirectStudent() {
        if (null == this.student)
            return true;

        return this.student.trim().length() == 0;
    }
}
