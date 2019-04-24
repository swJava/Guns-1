package com.stylefeng.guns.rest.modular.member.responser;

import com.stylefeng.guns.modular.system.model.Score;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/16 15:04
 * @Version 1.0
 */
@ApiModel(value = "ScoreResponse", description = "查分详情")
public class ScoreResponse extends Score {

    @ApiModelProperty(name = "classes", value = "可报名班级", position = 1)
    private List<Map<String, Object>> classes = new ArrayList<>();

    public List<Map<String, Object>> getClasses() {
        return classes;
    }

    public void setClasses(List<Map<String, Object>> classes) {
        this.classes = classes;
    }

    public void addClass(Map<String, Object> classInfo){
        this.classes.add(classInfo);
    }

    public static ScoreResponse me(Score score) {
        ScoreResponse response = new ScoreResponse();
        BeanUtils.copyProperties(score, response);

        StringTokenizer codeToken = null;
        StringTokenizer nameToken = null;
        if (null != score.getClassCodes()){
            codeToken = new StringTokenizer(score.getClassCodes(), ",");
        }

        if (null != score.getClassNames()){
            nameToken = new StringTokenizer(score.getClassNames(), ",");
        }

        while(codeToken.hasMoreTokens()){
            Map<String, Object> classInfo = new HashMap<>();

            String name = "";
            if (null != nameToken && nameToken.hasMoreTokens())
                name = nameToken.nextToken();

            classInfo.put(codeToken.nextToken(), name);
            response.addClass(classInfo);
        }
        return response;
    }
}
