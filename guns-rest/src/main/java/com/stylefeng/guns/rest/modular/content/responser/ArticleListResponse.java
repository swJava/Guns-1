package com.stylefeng.guns.rest.modular.content.responser;

import com.stylefeng.guns.modular.system.model.Content;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by 罗华.
 */
@ApiModel(value = "ArticleListResponse", description = "文章列表")
public class ArticleListResponse extends SimpleResponser {
    private static final long serialVersionUID = 3833433927999038296L;

    @ApiModelProperty(name = "data", value = "文章集合")
    private List<Content> data;

    public List<Content> getData() {
        return data;
    }

    public void setData(List<Content> data) {
        this.data = data;
    }

    public static Responser me(List<Content> contentList) {
        ArticleListResponse response = new ArticleListResponse();
        response.setCode(SUCCEED);
        response.setMessage("查询成功");
        response.setData(contentList);
        return response;
    }
}
