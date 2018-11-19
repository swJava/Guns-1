package com.stylefeng.guns.rest.modular.content.responser;

import com.stylefeng.guns.modular.system.model.Content;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 罗华.
 */
@ApiModel(value = "ArticleDetailResponse", description = "文章详情")
public class ArticleDetailResponse extends SimpleResponser {
    private static final long serialVersionUID = -4447560516750379198L;

    @ApiModelProperty(name = "data", value = "文章")
    private Content data;

    public Content getData() {
        return data;
    }

    public void setData(Content data) {
        this.data = data;
    }
}
