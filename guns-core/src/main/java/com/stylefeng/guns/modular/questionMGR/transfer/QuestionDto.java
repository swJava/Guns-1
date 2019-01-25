package com.stylefeng.guns.modular.questionMGR.transfer;

import com.stylefeng.guns.modular.system.model.Question;
import com.stylefeng.guns.modular.system.model.QuestionItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/24 14:09
 * @Version 1.0
 */
public class QuestionDto extends Question {

    private List<QuestionItem> questionItems = new ArrayList<QuestionItem>();

    public List<QuestionItem> getQuestionItems() {
        return questionItems;
    }

    public void setQuestionItems(List<QuestionItem> questionItems) {
        this.questionItems = questionItems;
    }
}
