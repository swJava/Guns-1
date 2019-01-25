package com.stylefeng.guns.modular.classMGR.transfer;

import com.stylefeng.guns.modular.system.model.ScheduleClass;
import com.stylefeng.guns.util.DateUtil;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/25 16:49
 * @Version 1.0
 */
public class ClassPlanDto extends ScheduleClass {

    private String className;

    private String courseName;

    private String classBeginTime;

    private String classEndTime;

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
}
