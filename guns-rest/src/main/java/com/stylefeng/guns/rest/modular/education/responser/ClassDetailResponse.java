package com.stylefeng.guns.rest.modular.education.responser;

import com.stylefeng.guns.modular.system.model.Class;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

/**
 * Created by 罗华.
 */
@ApiModel(value = "ClassDetailResponse", description = "班级详情")
public class ClassDetailResponse extends SimpleResponser {
    private static final long serialVersionUID = -140511623928497259L;

    @ApiModelProperty(name = "data", value = "班级")
    private ClassResponser data;

    public Class getData() {
        return data;
    }

    public void setData(Class classInfo) {
        ClassResponser dto = new ClassResponser();
        BeanUtils.copyProperties(classInfo, dto);
        dto.setClassTimeDesp(classInfo.getStudyTimeDesp());

        this.data = dto;
    }

    public static Responser me(Class classInfo) {
        ClassDetailResponse response = new ClassDetailResponse();

        response.setCode(SUCCEED);
        response.setMessage("查询成功");

        response.setData(classInfo);
        return response;
    }
}
