package com.stylefeng.guns.rest.modular.member.responser;

import com.stylefeng.guns.modular.system.model.Score;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.BeanUtils;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/16 15:04
 * @Version 1.0
 */
@ApiModel(value = "ScoreResponse", description = "查分详情")
public class ScoreResponse extends Score {

    public static ScoreResponse me(Score score) {
        ScoreResponse response = new ScoreResponse();
        BeanUtils.copyProperties(score, response);

        return response;
    }
}
