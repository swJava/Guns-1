package com.stylefeng.guns.rest.modular.content.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.contentMGR.service.IColumnActionService;
import com.stylefeng.guns.modular.contentMGR.service.IColumnService;
import com.stylefeng.guns.modular.contentMGR.service.IContentCategoryService;
import com.stylefeng.guns.modular.contentMGR.service.IContentService;
import com.stylefeng.guns.modular.system.model.Column;
import com.stylefeng.guns.modular.system.model.ColumnAction;
import com.stylefeng.guns.modular.system.model.Content;
import com.stylefeng.guns.modular.system.model.ContentCategory;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.modular.content.responser.ArticleDetailResponse;
import com.stylefeng.guns.rest.modular.content.responser.ArticleListResponse;
import com.stylefeng.guns.rest.modular.content.responser.NavigateListResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 罗华.
 */
@RestController
@RequestMapping("/content")
@Api(value = "ContentController", tags = "内容接口")
public class ContentController {

    @Autowired
    private IContentService contentService;

    @Autowired
    private IColumnService columnService;

    @Autowired
    private IColumnActionService columnActionService;

    @Autowired
    private IContentCategoryService contentCategoryService;


    @RequestMapping(value = "/navigate/list", method = RequestMethod.POST)
    @ApiOperation(value="导航信息列表", httpMethod = "POST", response = NavigateListResponse.class)
    public Responser navigateList(){
        Wrapper<Column> queryWrapper = new EntityWrapper<Column>();
        queryWrapper.eq("pcode", "LM000001");
        queryWrapper.eq("status", GenericState.Valid.code);
        List<Column> navList = columnService.selectList(queryWrapper);

        NavigateListResponse response = NavigateListResponse.me();
        for(Column nav : navList){
            Wrapper<ColumnAction> actionQueryWrapper = new EntityWrapper<ColumnAction>();

            actionQueryWrapper.eq("column_code", nav.getCode());
            actionQueryWrapper.eq("status", GenericState.Valid.code);
            actionQueryWrapper.eq("name", "select");

            ColumnAction action = columnActionService.selectOne(actionQueryWrapper);

            response.addNavigate(nav, action);
        }
        return response;
    }

    @RequestMapping(value = "/article/list", method = RequestMethod.POST)
    @ApiOperation(value="文章列表", httpMethod = "POST", response = ArticleListResponse.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "标题关键字", dataType = "String" ),
            @ApiImplicitParam(name = "code", value = "类别编码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "size", value = "数量", defaultValue = "5", dataType = "Integer")
    })
    public Responser articleList(
            String keyword,
            String code,
            Integer size){
        if (null == size)
            size = 5;

        if (null == code)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"栏目编码"});

        Wrapper<ContentCategory> queryWrapper = new EntityWrapper<ContentCategory>();
        queryWrapper.eq("status", GenericState.Valid.code);
        queryWrapper.eq("column_code", code);

        if (null != keyword)
            queryWrapper.like("content_name", keyword);

        queryWrapper.last(" limit " + size);

        List<ContentCategory> categoryList = contentCategoryService.selectList(queryWrapper);

        List<Content> contentList = new ArrayList<Content>();
        for (ContentCategory contentCategory : categoryList){
            Content content = contentService.get(contentCategory.getContentCode());
            if (null == content)
                continue;

            content.setContent(null);
            contentList.add(content);
        }

        return ArticleListResponse.me(contentList);
    }


    @RequestMapping(value = "/article/detail/{code}", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value="文章详情", httpMethod = "POST", response = ArticleDetailResponse.class)
    @ApiImplicitParam(name = "code", value = "内容编码", required = true, dataType = "String", example = "CT000001")
    public Responser detailForArticle(@PathVariable("code") String code){
        Content content = contentService.get(code);

        if (null == content)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        return ArticleDetailResponse.me(content);
    }
}
