package com.stylefeng.guns.rest.modular.education.responser;

import com.stylefeng.guns.modular.system.model.Course;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 罗华.
 */
@ApiModel(value = "CourseDetailResponse", description = "课程详情")
public class CourseDetailResponse extends SimpleResponser {
    private static final long serialVersionUID = 1822314486373030551L;

    @ApiModelProperty(name = "data", value = "课程")
    private Course data;

    public Course getData() {
        return data;
    }

    public void setData(Course data) {
        this.data = data;
    }
}
