package com.stylefeng.guns.rest.modular.education.responser;

import com.stylefeng.guns.modular.classMGR.transfer.ClassPlan;
import com.stylefeng.guns.modular.system.model.Class;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by 罗华.
 */
@ApiModel(value = "ClassDetailResponse", description = "班级详情")
public class ClassDetailResponse extends SimpleResponser {
    private static final long serialVersionUID = -140511623928497259L;

    @ApiModelProperty(name = "data", value = "班级")
    private ClassResponser data;

    public ClassResponser getData() {
        return data;
    }

    public void setData(ClassResponser data) {
        this.data = data;
    }

    public static Responser me(Class classInfo, List<ClassPlan> classPlanList) {
        ClassDetailResponse response = new ClassDetailResponse();

        response.setCode(SUCCEED);
        response.setMessage("查询成功");

        response.setData(ClassResponser.me(classInfo, classPlanList));
        return response;
    }
}
