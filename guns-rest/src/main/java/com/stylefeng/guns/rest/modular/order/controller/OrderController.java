package com.stylefeng.guns.rest.modular.order.controller;

import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import com.stylefeng.guns.rest.modular.order.requester.OrderPostRequester;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单
 * Created by 罗华.
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单接口")
public class OrderController {

    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ApiOperation(value="加入选课单", httpMethod = "POST", response = SimpleResponser.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", example = "18580255110"),
            @ApiImplicitParam(name = "student", value = "学员编码", required = true, dataType = "String", example = "XY000001"),
            @ApiImplicitParam(name = "classCode", value = "班级编码", required = true, dataType = "String", example = "BJ000001")
    }
    )
    public Responser 加入选课单(String userName, String student, String classCode){
        return null;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation(value="生成订单", httpMethod = "POST")
    public Responser 生成订单(OrderPostRequester requester){
        return null;
    }

    @ApiOperation(value="订单变更", httpMethod = "POST")
    @RequestMapping("/change")
    public Responser 订单变更(OrderPostRequester requester){
        return null;
    }
}
