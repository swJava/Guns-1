package com.stylefeng.guns.rest.modular.education.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/19 22:16
 * @Version 1.0
 */
@ApiModel(value = "ChangeApplyRequester", description = "转班申请")
public class ChangeApplyRequester extends SimpleRequester {


    @ApiModelProperty(name = "studentCode", value = "学员编码", required = true, position = 0, example = "XY181220000001")
    private String studentCode;

    @ApiModelProperty(name = "sourceClass", value = "调出班级编码", required = true, position = 1, example = "BJ000001")
    private String sourceClass;

    @ApiModelProperty(name = "targetClass", value = "调入班级编码", required = true, position = 2, example = "BJ000002")
    private String targetClass;

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getSourceClass() {
        return sourceClass;
    }

    public void setSourceClass(String sourceClass) {
        this.sourceClass = sourceClass;
    }

    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public boolean checkValidate() {
        return false;
    }
}
