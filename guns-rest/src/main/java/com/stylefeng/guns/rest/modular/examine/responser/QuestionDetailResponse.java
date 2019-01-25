package com.stylefeng.guns.rest.modular.examine.responser;

import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/22 18:40
 * @Version 1.0
 */
@ApiModel(value = "QuestionDetailResponse", description = "问题详情")
public class QuestionDetailResponse extends SimpleResponser {
    @ApiModelProperty(name = "data", value = "问题详情")
    private QuestionResponse data;

    public QuestionResponse getData() {
        return data;
    }

    public void setData(QuestionResponse data) {
        this.data = data;
    }

    public static Responser me(QuestionResponse questionResponse) {
        QuestionDetailResponse response = new QuestionDetailResponse();

        response.setCode(SUCCEED);
        response.setMessage("查询成功");

        response.setData(questionResponse);

        return response;
    }
}
