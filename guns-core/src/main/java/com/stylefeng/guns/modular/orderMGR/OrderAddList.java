package com.stylefeng.guns.modular.orderMGR;

import com.stylefeng.guns.modular.system.model.OrderItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/30 7:26
 * @Version 1.0
 */
@ApiModel
public class OrderAddList extends HashSet<OrderItem> {
}
