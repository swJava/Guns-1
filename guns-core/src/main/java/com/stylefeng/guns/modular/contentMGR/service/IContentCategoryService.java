package com.stylefeng.guns.modular.contentMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.contentMGR.service.impl.ContentServiceImpl;
import com.stylefeng.guns.modular.system.model.Column;
import com.stylefeng.guns.modular.system.model.Content;
import com.stylefeng.guns.modular.system.model.ContentCategory;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/26 23:35
 * @Version 1.0
 */
public interface IContentCategoryService extends IService<ContentCategory> {
    /**
     * 新建
     * @param content
     * @param columnEntity
     */
    void create(Content content, Column columnEntity);

    /**
     * 从所有栏目删除
     *
     * @param code
     */
    void delete(String code);

    /**
     * 从指定栏目删除
     *
     * @param code
     * @param removeColumns
     */
    void delete(String code, String[] removeColumns);
}
