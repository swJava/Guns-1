package com.stylefeng.guns.rest.modular.education.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 罗华.
 */
@ApiModel(value = "AdjustRequester", description = "调课申请")
public class AdjustApplyRequester extends SimpleRequester {
    private static final long serialVersionUID = -6420089033426500270L;

    @ApiModelProperty(name = "studentCode", value = "学员编码", required = true, position = 0, example = "XY181220000001")
    private String studentCode;

    @ApiModelProperty(name = "outlineCode", value = "调整课时编码", required = true, position = 1, example = "KS000001")
    private String outlineCode;

    @ApiModelProperty(name = "targetClass", value = "调入班级编码", required = true, position = 2, example = "BJ000001")
    private String targetClass;

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getOutlineCode() {
        return outlineCode;
    }

    public void setOutlineCode(String outlineCode) {
        this.outlineCode = outlineCode;
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
