package com.stylefeng.guns.modular.classMGR.transfer;

import com.stylefeng.guns.modular.system.model.ScheduleClass;
import com.stylefeng.guns.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/25 16:49
 * @Version 1.0
 */
@ApiModel(value = "ClassPlan", description = "排班计划")
public class ClassPlan extends ScheduleClass {

    @ApiModelProperty(name = "className", value = "班级名称", example = "春季班小学一年级（启航）")
    private String className;

    @ApiModelProperty(name = "courseName", value = "课程名称名称", example = "春季班小学一年级（启航）")
    private String courseName;

    private String classBeginTime;

    private String classEndTime;

    @ApiModelProperty(name = "description", value = "排课计划介绍", example = "排课计划介绍")
    private String description;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getDescription() {
        return this.getClassTime().substring(0, 2)
                + ":"
                + this.getClassTime().substring(2)
                + " ~ "
                + this.getEndTime().substring(0, 2)
                + ":"
                + this.getEndTime().substring(2)
                + this.getClassName();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassBeginTime() {
        String time = DateUtil.format(this.getStudyDate(), "yyyy-MM-dd");
        time += "T" + this.getClassTime().substring(0, 2) + ":" + this.getClassTime().substring(2) + ":00";
        return time;
    }

    public void setClassBeginTime(String classBeginTime) {
        this.classBeginTime = classBeginTime;
    }

    public String getClassEndTime() {
        String time = DateUtil.format(this.getStudyDate(), "yyyy-MM-dd");
        time += "T" + this.getEndTime().substring(0, 2) + ":" + this.getEndTime().substring(2) + ":00";
        return time;
    }

    public void setClassEndTime(String classEndTime) {
        this.classEndTime = classEndTime;
    }

    public Integer getClassDuration() {

        Date beginDate = DateUtil.parse("20190101" + this.getClassTime() + "00", "yyyyMMddHHmmss");
        Date endDate = DateUtil.parse("20190101" + this.getEndTime() + "00", "yyyyMMddHHmmss");

        return DateUtil.getMinuteSub(beginDate, endDate);
    }

    public static void main(String[] args){
        String begin = "0915";
        String end = "1115";

        ClassPlan classPlanDto = new ClassPlan();
        classPlanDto.setClassTime(begin);
        classPlanDto.setEndTime(end);
        System.out.println(classPlanDto.getClassDuration());
    }
}
