package com.stylefeng.guns.rest.modular.examine.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 罗华.
 */
@ApiModel(value = "ExamPaperSubmitRequester", description = "提交考卷")
public class ExamPaperSubmitRequester extends SimpleRequester {
    private static final long serialVersionUID = -4015676963558662632L;
    /**
     * 试卷编码
     */
    @ApiModelProperty(name = "code", value = "试卷编码", example = "SJ00000001")
    private String code;

    /**
     * 答案
     */
    private String answer;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean checkValidate() {
        return false;
    }
}
