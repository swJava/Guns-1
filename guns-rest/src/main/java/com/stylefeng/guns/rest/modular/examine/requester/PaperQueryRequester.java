package com.stylefeng.guns.rest.modular.examine.requester;

import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/22 9:12
 * @Version 1.0
 */
@ApiModel(value = "PaperQueryRequester", description = "试卷列表查询")
public class PaperQueryRequester extends SimpleRequester {
    @ApiModelProperty(value = "student", name = "学员信息", required = true)
    @NotNull(message = "学员信息不能为空")
    private Student student;

    @ApiModelProperty(value = "classCode", name = "班级信息")
    private String classCode;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
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
