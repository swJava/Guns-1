package com.stylefeng.guns.rest.modular.examine.responser;

import com.stylefeng.guns.modular.system.model.Question;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/22 15:43
 * @Version 1.0
 */
@ApiModel(value = "ExamineOutlineResponse", description = "考试大纲")
public class ExamineOutlineResponse extends SimpleResponser {
    @ApiModelProperty(name = "questionIndex", value = "试题集合")
    private List<Question> questionIndex = new ArrayList<>();

    public List<Question> getQuestionIndex() {
        return questionIndex;
    }

    public void setQuestionIndex(List<Question> questionIndex) {
        this.questionIndex = questionIndex;
    }

    public static Responser me(Collection<Question> questionList) {
        ExamineOutlineResponse response = new ExamineOutlineResponse();

        response.setCode(SUCCEED);
        response.setMessage("查询成功");

        response.getQuestionIndex().addAll(questionList);
        return response;
    }
}
