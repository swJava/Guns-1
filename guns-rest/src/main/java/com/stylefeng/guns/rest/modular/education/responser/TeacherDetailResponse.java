package com.stylefeng.guns.rest.modular.education.responser;

import com.stylefeng.guns.modular.system.model.Teacher;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 罗华.
 */
@ApiModel(value = "TeacherDetailResponse", description = "教师详情")
public class TeacherDetailResponse extends SimpleResponser {
    private static final long serialVersionUID = 5241474538027627962L;

    @ApiModelProperty(name = "data", value = "教师")
    private Teacher data;

    public Teacher getData() {
        return data;
    }

    public void setData(Teacher data) {
        this.data = data;
    }
}
