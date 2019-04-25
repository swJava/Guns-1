package com.stylefeng.guns.rest.modular.examine.requester;

import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/30 18:43
 * @Version 1.0
 */
@ApiModel(value = "BeginExamineRequester", description = "开始测试信息")
public class BeginExamineRequester extends SimpleRequester {

    @ApiModelProperty(name = "student", value = "学员编码", required = true, example = "XY181201000001")
    @NotBlank(message = "学员编码不能为空")
    private String student;
    @ApiModelProperty(name = "paperCode", value = "试卷编码", required = true, example = "SJ000001")
    @NotBlank(message = "试卷编码不能为空")
    private String paperCode;

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }

    @Override
    public boolean checkValidate() {
        return false;
    }
}
