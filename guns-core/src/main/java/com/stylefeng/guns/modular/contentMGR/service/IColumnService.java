package com.stylefeng.guns.modular.contentMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.node.ZTreeNode2nd;
import com.stylefeng.guns.modular.system.model.Attachment;
import com.stylefeng.guns.modular.system.model.Column;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/26 19:49
 * @Version 1.0
 */
public interface IColumnService extends IService<Column> {
    /**
     * 创建栏目
     *
     * @param column
     */
    void create(Column column);

    /**
     *
     * @param column
     * @return
     */
    Column get(String column);

    /**
     * 加入文章
     *
     * @param column
     * @param contents
     */
    void addContent(String column, Collection<String> contents);

    /**
     * 移除文章
     *
     * @param column
     * @param contentCodes
     */
    void removeContent(String column, Set<String> contentCodes);
}
