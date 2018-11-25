package com.stylefeng.guns.rest.modular.education.responser;

import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 罗华.
 */
@ApiModel(value = "ClassroomDetailResponse", description = "教室详情")
public class ClassroomDetailResponse extends SimpleResponser {
    private static final long serialVersionUID = 3394437649611584810L;

    @ApiModelProperty(name = "data", value = "教室")
    private Classroom data;

    public Classroom getData() {
        return data;
    }

    public void setData(Classroom data) {
        this.data = data;
    }

    public static Responser me(Classroom classroom) {
        ClassroomDetailResponse response = new ClassroomDetailResponse();
        response.setCode(SUCCEED);
        response.setMessage("查询成功");

        response.setData(classroom);
        return response;
    }
}
