package com.stylefeng.guns.rest.modular.education.responser;

import com.stylefeng.guns.modular.system.model.Class;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 罗华.
 */
@ApiModel(value = "ClassDetailResponse", description = "班级详情")
public class ClassDetailResponse extends SimpleResponser {
    private static final long serialVersionUID = -140511623928497259L;

    @ApiModelProperty(name = "data", value = "班级")
    private com.stylefeng.guns.modular.system.model.Class data;

    public Class getData() {
        return data;
    }

    public void setData(Class data) {
        this.data = data;
    }
}
