package com.stylefeng.guns.rest.modular.content.controller;

import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.modular.content.responser.ArticleDetailResponse;
import com.stylefeng.guns.rest.modular.content.responser.ArticleListResponse;
import com.stylefeng.guns.rest.modular.content.responser.NavigateListResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 罗华.
 */
@RestController
@RequestMapping("/content")
@Api(value = "ContentController", tags = "内容接口")
public class ContentController {

    @RequestMapping(value = "/navigate/list", method = RequestMethod.POST)
    @ApiOperation(value="导航信息列表", httpMethod = "POST", response = NavigateListResponse.class)
    public Responser 导航信息(){
        return null;
    }

    @RequestMapping(value = "/article/list", method = RequestMethod.POST)
    @ApiOperation(value="文章列表", httpMethod = "POST", response = ArticleListResponse.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "from", value = "类型", required = true, dataType = "Integer" ),
            @ApiImplicitParam(name = "code", value = "类别编码", required = true, dataType = "String")
    })
    public Responser 文章列表(Integer from, String code){
        return null;
    }


    @RequestMapping(value = "/article/detail/{code}", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value="文章详情", httpMethod = "POST", response = ArticleDetailResponse.class)
    @ApiImplicitParam(name = "code", value = "内容编码", required = true, dataType = "String", example = "CT000001")
    public Responser 文章详情(@PathVariable("code") String code){
        return null;
    }
}
