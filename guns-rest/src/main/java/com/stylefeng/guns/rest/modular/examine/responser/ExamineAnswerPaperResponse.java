package com.stylefeng.guns.rest.modular.examine.responser;

import com.stylefeng.guns.modular.system.model.Class;

import java.io.Serializable;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/2/19 23:59
 * @Version 1.0
 */
public class ExamineAnswerPaperResponse implements Serializable {

    public static ExamineAnswerPaperResponse me(Map<String, Object> examineAnswerPaper, Class classInfo) {

        ExamineAnswerPaperResponse response = new ExamineAnswerPaperResponse();

        return response;
    }
}
