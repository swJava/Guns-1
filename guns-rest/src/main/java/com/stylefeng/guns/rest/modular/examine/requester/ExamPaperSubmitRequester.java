package com.stylefeng.guns.rest.modular.examine.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;

/**
 * Created by 罗华.
 */
public class ExamPaperSubmitRequester extends SimpleRequester {
    private static final long serialVersionUID = -4015676963558662632L;
    /**
     * 试卷编码
     */
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
