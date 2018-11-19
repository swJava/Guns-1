package com.stylefeng.guns.rest.modular.education.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 罗华.
 */
@ApiModel(value = "AdjustRequester", description = "调课/班申请")
public class AdjustApplyRequester extends SimpleRequester {
    private static final long serialVersionUID = -6420089033426500270L;

    @ApiModelProperty(name = "studentCode", value = "学员编码", required = true, position = 0, example = "XY22222")
    private String studentCode;
    @ApiModelProperty(name = "userName", value = "用户名", required = true, position = 1, example = "XY22222")
    private String userName;
    @ApiModelProperty(name = "targetCode", value = "调整申请编码", required = true, position = 2, example = "BJ1111")
    private String targetCode;

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    @Override
    public boolean checkValidate() {
        return false;
    }
}
