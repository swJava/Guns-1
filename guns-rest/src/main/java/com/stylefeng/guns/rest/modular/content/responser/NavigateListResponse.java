package com.stylefeng.guns.rest.modular.content.responser;

import com.stylefeng.guns.modular.system.model.Column;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by 罗华.
 */
@ApiModel(value = "NavigateListResponse", description = "导航栏目列表")
public class NavigateListResponse extends SimpleResponser {
    private static final long serialVersionUID = 6603654982583000301L;

    @ApiModelProperty(name = "data", value = "导航栏目集合")
    private List<Column> data;

    public List<Column> getData() {
        return data;
    }

    public void setData(List<Column> data) {
        this.data = data;
    }
}
