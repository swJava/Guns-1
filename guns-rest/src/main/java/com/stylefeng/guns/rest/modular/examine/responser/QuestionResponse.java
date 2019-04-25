package com.stylefeng.guns.rest.modular.examine.responser;

import com.stylefeng.guns.modular.system.model.Question;
import com.stylefeng.guns.modular.system.model.QuestionItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/22 15:48
 * @Version 1.0
 */
@ApiModel(value = "QuestionResponse", description = "试题")
public class QuestionResponse extends Question {
    @ApiModelProperty(name = "items", value = "答案项集合")
    private List<QuestionItem> items = new ArrayList<>();

    public List<QuestionItem> getItems() {
        return items;
    }

    public void setItems(List<QuestionItem> items) {
        this.items = items;
    }

    public static QuestionResponse me(Question question) {
        QuestionResponse response = new QuestionResponse();
        BeanUtils.copyProperties(question, response);
        return response;
    }
}
