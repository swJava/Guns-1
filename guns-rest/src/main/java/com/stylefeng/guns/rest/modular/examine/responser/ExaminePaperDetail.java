package com.stylefeng.guns.rest.modular.examine.responser;

import com.stylefeng.guns.modular.system.model.ExaminePaper;
import com.stylefeng.guns.rest.core.Responser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/2/20 01:43
 * @Version 1.0
 */
@ApiModel(value = "ExaminePaperDetail", description = "试卷详情")
public class ExaminePaperDetail extends ExaminePaper {

    @ApiModelProperty(name = "teacherName", value = "出题老师名称", position = 8, example = "李敏")
    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public static ExaminePaperDetail me(ExaminePaper examinePaper) {
        ExaminePaperDetail detail = new ExaminePaperDetail();

        BeanUtils.copyProperties(examinePaper, detail);

        return detail;
    }
}
