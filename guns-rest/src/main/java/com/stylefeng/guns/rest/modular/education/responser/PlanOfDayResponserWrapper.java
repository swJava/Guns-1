package com.stylefeng.guns.rest.modular.education.responser;

import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/16 23:25
 * @Version 1.0
 */
@ApiModel( value = "PlanOfDayResponserWrapper", description = "单天课程表")
public class PlanOfDayResponserWrapper extends SimpleResponser {
    @ApiModelProperty(name = "plans", value = "课程表集合")
    private List<PlanOfDayResponser> plans = new ArrayList<PlanOfDayResponser>();

    public List<PlanOfDayResponser> getPlans() {
        return plans;
    }

    public void setPlans(List<PlanOfDayResponser> plans) {
        this.plans = plans;
    }

    public static PlanOfDayResponserWrapper me(List<PlanOfDayResponser> responserList) {
        PlanOfDayResponserWrapper wrapper = new PlanOfDayResponserWrapper();

        wrapper.setCode(SUCCEED);
        wrapper.setMessage("查询成功");

        wrapper.setPlans(responserList);

        return wrapper;
    }
}
