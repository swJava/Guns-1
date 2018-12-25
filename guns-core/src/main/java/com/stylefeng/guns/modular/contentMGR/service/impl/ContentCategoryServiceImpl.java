package com.stylefeng.guns.modular.contentMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.contentMGR.service.IContentCategoryService;
import com.stylefeng.guns.modular.system.dao.ContentCategoryMapper;
import com.stylefeng.guns.modular.system.model.Column;
import com.stylefeng.guns.modular.system.model.Content;
import com.stylefeng.guns.modular.system.model.ContentCategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/26 23:35
 * @Version 1.0
 */
@Service
public class ContentCategoryServiceImpl extends ServiceImpl<ContentCategoryMapper, ContentCategory> implements IContentCategoryService {

    @Override
    public void create(Content content, Column column) {
        if (null == content)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS);

        if (null == column)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS);

        List<ContentCategory> relationList = selectList(new EntityWrapper<ContentCategory>().eq("content_code", content.getCode()).eq("column_code", column.getCode()));

        if (null != relationList && relationList.size() > 0){
            deleteBatchIds(relationList);
        }

        // 新增
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setContentCode(content.getCode());
        contentCategory.setContentName(content.getTitle());
        contentCategory.setColumnCode(column.getCode());
        contentCategory.setColumnName(column.getName());
        contentCategory.setStatus(GenericState.Valid.code);

        insert(contentCategory);
    }

    @Override
    public void delete(String contentCode) {
        if (null == contentCode)
            return;

        Wrapper<ContentCategory> relationQueryWrapper = new EntityWrapper<ContentCategory>();
        relationQueryWrapper.eq("content_code", contentCode);

        List<ContentCategory> relationList = selectList(relationQueryWrapper);

        if (null == relationList || relationList.isEmpty())
            return;

        String[] removeColumns = new String[relationList.size()];
        int idx = 0;
        for(ContentCategory contentCategory : relationList){
            removeColumns[idx++] = contentCategory.getColumnCode();
        }

        delete(contentCode, removeColumns);
    }

    @Override
    public void delete(String contentCode, String[] removeColumns) {

        if (null == contentCode)
            return;

        if (null == removeColumns || removeColumns.length == 0)
            return;

        Wrapper<ContentCategory> queryWrapper = new EntityWrapper<ContentCategory>();

        queryWrapper.eq("content_code", contentCode);
        queryWrapper.in("column_code", removeColumns);

        delete(queryWrapper);
    }
}
