package com.stylefeng.guns.rest.modular.education.responser;

import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by 罗华.
 */
@ApiModel(value = "ClassListResponse", description = "班级列表")
public class ClassListResponse extends SimpleResponser {
    private static final long serialVersionUID = 4265927729370374968L;

    @ApiModelProperty(name = "data", value = "班级集合")
    private List<Class> data;

    public List<Class> getData() {
        return data;
    }

    public void setData(List<Class> data) {
        this.data = data;
    }
}
