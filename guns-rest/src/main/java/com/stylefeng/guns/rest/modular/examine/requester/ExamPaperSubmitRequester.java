package com.stylefeng.guns.rest.modular.examine.requester;

import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.rest.core.SimpleRequester;
import com.stylefeng.guns.rest.modular.examine.responser.QuestionResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 罗华.
 */
@ApiModel(value = "ExamPaperSubmitRequester", description = "提交考卷")
public class ExamPaperSubmitRequester extends SimpleRequester {
    private static final long serialVersionUID = -4015676963558662632L;
    /**
     * 试卷编码
     */
    @ApiModelProperty(name = "code", value = "答卷编码", example = "DJ00000001")
    private String code;

    /**
     * 答案
     */
    @ApiModelProperty(name = "code", value = "试卷编码", example = "[{}]")
    private String answer;

    @ApiModelProperty(hidden = true)
    private List<QuestionRequester> submitItems = new ArrayList<>();

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

    public List<QuestionRequester> getSubmitItems() {
        return submitItems;
    }

    public void setSubmitItems(List<QuestionRequester> submitItems) {
        this.submitItems = submitItems;
    }

    @Override
    public boolean checkValidate() {
        return false;
    }

    public void parseSubmit() {
        submitItems.addAll(JSON.parseArray(this.answer, QuestionRequester.class));
    }
}
