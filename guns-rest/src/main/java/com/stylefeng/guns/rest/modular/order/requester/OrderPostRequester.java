package com.stylefeng.guns.rest.modular.order.requester;

import com.stylefeng.guns.modular.orderMGR.OrderAddList;
import com.stylefeng.guns.modular.orderMGR.OrderDelList;
import com.stylefeng.guns.modular.orderMGR.OrderUpdateList;
import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * Created by 罗华.
 */
@ApiModel(value = "OrderPostRequester", description = "提交订单信息")
public class OrderPostRequester extends SimpleRequester {
    private static final long serialVersionUID = -4959430136773709060L;

    @ApiModelProperty(name = "service", value = "业务类型", example = "Order")
    @NotBlank(message = "业务类型不能为空")
    private String service;

    @ApiModelProperty(name = "addList", value = "添加项目")
    private OrderAddList addList;

    @ApiModelProperty(name = "updateList", value = "变更项目")
    private OrderUpdateList updateList;

    @ApiModelProperty(name = "delList", value = "删除项目")
    private OrderDelList delList;

    @ApiModelProperty(name = "payMethod", value = "支付方式： ", example = "22")
    private Integer payMethod;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public OrderAddList getAddList() {
        return addList;
    }

    public void setAddList(OrderAddList addList) {
        this.addList = addList;
    }

    public OrderUpdateList getUpdateList() {
        return updateList;
    }

    public void setUpdateList(OrderUpdateList updateList) {
        this.updateList = updateList;
    }

    public OrderDelList getDelList() {
        return delList;
    }

    public void setDelList(OrderDelList delList) {
        this.delList = delList;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    @Override
    public boolean checkValidate() {
        return false;
    }
}
