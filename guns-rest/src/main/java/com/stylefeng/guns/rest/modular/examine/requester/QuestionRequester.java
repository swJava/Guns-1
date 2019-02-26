package com.stylefeng.guns.rest.modular.examine.requester;

import com.stylefeng.guns.modular.system.model.Question;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/23 15:30
 * @Version 1.0
 */
public class QuestionRequester extends Question {
    /**
     * 学生答案
     */
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
