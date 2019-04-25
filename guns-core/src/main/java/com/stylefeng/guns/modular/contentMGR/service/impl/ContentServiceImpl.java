package com.stylefeng.guns.modular.contentMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.contentMGR.service.IColumnService;
import com.stylefeng.guns.modular.contentMGR.service.IContentCategoryService;
import com.stylefeng.guns.modular.contentMGR.service.IContentService;
import com.stylefeng.guns.modular.system.dao.ContentMapper;
import com.stylefeng.guns.modular.system.model.Column;
import com.stylefeng.guns.modular.system.model.Content;
import com.stylefeng.guns.util.CodeKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.CacheableOperation;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 内容 服务实现类
 * </p>
 *
 * @author simple.song
 * @since 2018-10-25
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements IContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private IColumnService columnService;

    @Autowired
    private IContentCategoryService contentCategoryService;

    @Override
    public Content get(String code) {
        if (null == code)
            return null;

        Wrapper<Content> queryContent = new EntityWrapper<Content>();
        queryContent.eq("code", code);

        return selectOne(queryContent);
    }

    @Override
    public void create(Content content) {
        Date now = new Date();
        content.setCode(CodeKit.generateContent());
        content.setCreateDate(now);

        insert(content);
    }

    @Override
    public List<Content> findArticle(String column) {

        Map<String, Object> queryMap = new HashMap<String , Object>();
        queryMap.put("column", column);

        List<Content> resultList = contentMapper.selectByColumn(queryMap);

        return resultList;
    }

    @Override
    public List<Content> findArticleOutline(String column) {
        Map<String, Object> queryMap = new HashMap<String , Object>();
        queryMap.put("column", column);

        List<Content> resultList = contentMapper.selectOutlineByColumn(queryMap);

        return resultList;
    }

    @Override
    public void createAndPutInColumn(Content content, String column) {

        if (null == column)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS);

        Column columnEntity = columnService.get(column);

        if (null == column)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS);

        create(content);

        contentCategoryService.create(content, columnEntity);
    }

    @Override
    public void delete(String contentCode, String[] removeColumns) {
        if (null == contentCode)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS);

        Content content = get(contentCode);

        if (null == content)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        deleteById(content);

        contentCategoryService.delete(content.getCode(), removeColumns);
    }

    @Override
    public Page<Map<String, Object>> selectMapsPage(Set<String> includeColumnList, Set<String> excludeColumnList, Map<String, Object> queryMap) {

        Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage();
        if (null != includeColumnList){
            queryMap.put("includeList", includeColumnList);
        }

        if (null != excludeColumnList){
            queryMap.put("excludeList", excludeColumnList);
        }

        List<Map<String, Object>> resultMap = contentMapper.selectByColumns(page, queryMap);

        page.setRecords(resultMap);
        return page;
    }
}
