package com.stylefeng.guns.rest.modular.education.responser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/16 21:40
 * @Version 1.0
 */
@ApiModel(value = "ClassDto", description = "班级扩展")
public class ClassDto extends com.stylefeng.guns.modular.system.model.Class {

    @ApiModelProperty(name = "classTimeDesp", value = "上课时间描述", example = "每周五、周六 09:00 ~ 10:30")
    String classTimeDesp;

    @ApiModelProperty(name = "canAdjust", value = "能否调课", example = "false")
    boolean canAdjust;

    @ApiModelProperty(name = "canChange", value = "能否转班", example = "true")
    boolean canChange;

    public String getClassTimeDesp() {
        return classTimeDesp;
    }

    public void setClassTimeDesp(String classTimeDesp) {
        this.classTimeDesp = classTimeDesp;
    }

    public boolean isCanAdjust() {
        return canAdjust;
    }

    public void setCanAdjust(boolean canAdjust) {
        this.canAdjust = canAdjust;
    }

    public boolean isCanChange() {
        return canChange;
    }

    public void setCanChange(boolean canChange) {
        this.canChange = canChange;
    }
}
