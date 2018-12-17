package com.stylefeng.guns.rest.modular.education.responser;

import com.stylefeng.guns.modular.system.model.CourseOutline;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/16 23:34
 * @Version 1.0
 */
@ApiModel(value = "PlanOfDayResponser", description = "单天课程表")
public class PlanOfDayResponser {
    @ApiModelProperty(name = "outline", value = "课时")
    String outline;
    @ApiModelProperty(name = "classInfo", value = "班级")
    ClassResponser classInfo;

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public ClassResponser getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassResponser classInfo) {
        this.classInfo = classInfo;
    }

    public static PlanOfDayResponser me(ClassResponser classInfo, CourseOutline outline) {
        PlanOfDayResponser responser = new PlanOfDayResponser();
        responser.setClassInfo(classInfo);
        responser.setOutline(outline.getOutline());
        return responser;
    }
}
