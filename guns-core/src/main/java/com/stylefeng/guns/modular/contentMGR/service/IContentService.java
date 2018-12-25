package com.stylefeng.guns.modular.contentMGR.service;

import com.stylefeng.guns.modular.system.model.Content;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 内容 服务类
 * </p>
 *
 * @author simple.song
 * @since 2018-10-25
 */
public interface IContentService extends IService<Content> {

    /**
     * 根据编码获取内容
     *
     * @param contentCode
     * @return
     */
    Content get(String contentCode);

    /**
     * 新增内容
     *
     * @param content
     */
    void create(Content content);

    /**
     * 获取栏目下所有内容
     *
     * @param column
     * @return
     */
    List<Content> findArticle(String column);

    /**
     * 根据条件查询文章列表
     *
     * 只收集主要信息
     *
     * @param column
     * @return
     */
    List<Content> findArticleOutline(String column);

    /**
     * 新增内容并放入栏目
     *
     * @param content
     * @param column
     */
    void createAndPutInColumn(Content content, String column);

    /**
     * 删除
     *
     * @param contentCode
     * @param strings
     */
    void delete(String contentCode, String[] strings);
}
