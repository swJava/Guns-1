package com.stylefeng.guns.rest.modular.member.responser;

import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import com.stylefeng.guns.rest.modular.education.responser.ClassResponser;
import com.stylefeng.guns.rest.modular.order.responser.OrderListResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/17 1:42
 * @Version 1.0
 */
@ApiModel(value = "MyClassListResponser", description = "报班列表")
public class MyClassListResponser extends SimpleResponser {

    @ApiModelProperty(name = "data", value = "报班集合")
    private List<ClassResponser> data = new ArrayList<ClassResponser>();

    public List<ClassResponser> getData() {
        return data;
    }

    public void setData(List<ClassResponser> data) {
        this.data = data;
    }

    public static Responser me(List<ClassResponser> classList) {
        MyClassListResponser response = new MyClassListResponser();
        response.setCode(SUCCEED);
        response.setMessage("查询成功");
        response.setData(classList);
        return response;
    }
}
