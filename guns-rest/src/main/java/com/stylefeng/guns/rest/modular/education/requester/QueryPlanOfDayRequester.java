package com.stylefeng.guns.rest.modular.education.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;
import com.stylefeng.guns.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/16 22:42
 * @Version 1.0
 */
@ApiModel(value = "PlanQueryRequester", description = "当天课程表查询")
public class QueryPlanOfDayRequester extends SimpleRequester {
    @ApiModelProperty(name = "student", value = "学员编码", required = false, position = 0, example = "XY181211000001")
    private String student;

    @ApiModelProperty(name = "day", value = "查询日期", required = true, position = 1, example = "2018-12-16")
    @NotNull(message = "查询日期不能为空")
    private Date day;

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public void setDay(String day) {
        if (null == day)
            return;
        try {
            this.day = DateUtil.parse(day, "yyyy-MM-dd");
        }catch(Exception e){}
    }

    @Override
    public boolean checkValidate() {
        return false;
    }
}
