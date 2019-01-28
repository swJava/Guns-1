package com.stylefeng.guns.rest.modular.education.responser;

import com.stylefeng.guns.modular.classMGR.transfer.ClassPlan;
import com.stylefeng.guns.modular.education.transfer.StudentPlan;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/28 8:50
 * @Version 1.0
 */
public class PlanListResponser extends SimpleResponser {

    private List<StudentPlan> planList = new ArrayList<StudentPlan>();

    public List<StudentPlan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<StudentPlan> planList) {
        this.planList = planList;
    }

    public static Responser me(List<StudentPlan> planList) {
        PlanListResponser response = new PlanListResponser();

        response.setCode(SUCCEED);
        response.setMessage("查询成功");

        response.setPlanList(planList);
        return response;
    }
}
