package com.stylefeng.guns.rest.modular.examine.requester;

import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.rest.core.SimpleRequester;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/2/18 22:48
 * @Version 1.0
 */
@ApiModel(value = "ClassPaperFinderRequester", description = "试卷准备")
public class ClassPaperFinderRequester extends SimpleRequester {

    @ApiModelProperty(value = "student", name = "学员信息", required = true, example = "XY190202000001")
    @NotNull(message = "学员信息不能为空")
    private String student;

    @ApiModelProperty(value = "classCode", name = "班级信息", required = true, example = "BJ000003")
    @NotNull(message = "班级信息不能为空")
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
