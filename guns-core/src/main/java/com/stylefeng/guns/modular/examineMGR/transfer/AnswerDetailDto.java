package com.stylefeng.guns.modular.examineMGR.transfer;

import com.stylefeng.guns.modular.questionMGR.transfer.QuestionDto;
import com.stylefeng.guns.modular.system.model.AnswerDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/24 14:07
 * @Version 1.0
 */
public class AnswerDetailDto extends AnswerDetail{

    private List<QuestionDto> questionInfos = new ArrayList<QuestionDto>();

    public List<QuestionDto> getQuestionInfos() {
        return questionInfos;
    }

    public void setQuestionInfos(List<QuestionDto> questionInfos) {
        this.questionInfos = questionInfos;
    }
}
