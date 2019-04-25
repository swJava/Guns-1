package com.stylefeng.guns.modular.contentMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.core.node.ZTreeNode2nd;
import com.stylefeng.guns.modular.contentMGR.service.IColumnService;
import com.stylefeng.guns.modular.contentMGR.service.IContentCategoryService;
import com.stylefeng.guns.modular.contentMGR.service.IContentService;
import com.stylefeng.guns.modular.system.dao.ColumnMapper;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.modular.system.service.IAttachmentService;
import com.stylefeng.guns.util.CodeKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/26 19:50
 * @Version 1.0
 */
@Service
public class ColumnServiceImpl extends ServiceImpl<ColumnMapper, Column> implements IColumnService {
    private static final Logger log = LoggerFactory.getLogger(ColumnServiceImpl.class);

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private IContentService contentService;

    @Autowired
    private IContentCategoryService contentCategoryService;



    @Override
    public void create(Column column) {

        if (null == column)
            throw new ServiceException("");

        column.setCode(CodeKit.generateColumn());
        insert(column);
    }

    @Override
    public Column get(String code) {
        if (null == code)
            return null;

        return selectOne(new EntityWrapper<Column>().eq("code", code));
    }

    @Override
    public void addContent(String column, Collection<String> contents) {
        if(null == column)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"栏目"});

        Column currColumn = get(column);
        if (null == currColumn)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"栏目"});

        List<ContentCategory> relContentList = new ArrayList<ContentCategory>();
        for(String content : contents){
            Content currContent = contentService.get(content);
            if (null == currContent)
                continue;

            ContentCategory contentCategory = new ContentCategory();
            contentCategory.setColumnCode(currColumn.getCode());
            contentCategory.setColumnName(currColumn.getName());
            contentCategory.setContentCode(currContent.getCode());
            contentCategory.setContentName(currContent.getTitle());
            contentCategory.setStatus(GenericState.Valid.code);

            relContentList.add(contentCategory);
        }

        contentCategoryService.createBatch(relContentList);

    }

    @Override
    public void removeContent(String column, Set<String> contents) {
        if(null == column)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"栏目"});

        Column currColumn = get(column);
        if (null == currColumn)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"栏目"});

        List<ContentCategory> relContentList = new ArrayList<ContentCategory>();
        for(String content : contents){
            Wrapper<ContentCategory> queryWrapper = new EntityWrapper<ContentCategory>();
            queryWrapper.eq("column_code", currColumn.getCode());
            queryWrapper.eq("content_code", content);
            contentCategoryService.delete(queryWrapper);
        }
    }

    @Override
    public void doPause(String code) {
        if (null == code)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"栏目编码"});

        Column column = get(code);

        if (null == column)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"栏目"});

        column.setStatus(GenericState.Invalid.code);

        updateById(column);
    }

    @Override
    public void doResume(String code) {
        if (null == code)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"栏目编码"});

        Column column = get(code);

        if (null == column)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"栏目"});

        column.setStatus(GenericState.Valid.code);

        updateById(column);
    }
}
