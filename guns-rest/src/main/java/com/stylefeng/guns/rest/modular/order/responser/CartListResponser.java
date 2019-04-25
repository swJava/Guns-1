package com.stylefeng.guns.rest.modular.order.responser;

import com.stylefeng.guns.modular.system.model.CourseCart;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/17 21:36
 * @Version 1.0
 */
@ApiModel(value = "CartListResponser", description = "选课单列表")
public class CartListResponser extends SimpleResponser {

    @ApiModelProperty(name = "data", value = "选课单集合")
    private List<CourseCart> data;

    public List<CourseCart> getData() {
        return data;
    }

    public void setData(List<CourseCart> data) {
        this.data = data;
    }

    public static Responser me(List<CourseCart> courseCartList) {
        CartListResponser response = new CartListResponser();
        response.setCode(SUCCEED);
        response.setMessage("查询成功");
        response.setData(courseCartList);
        return response;
    }
}
