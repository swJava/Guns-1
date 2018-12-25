package com.stylefeng.guns.modular.contentMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Attachment;
import com.stylefeng.guns.modular.system.model.Column;

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
     * @param icon
     */
    void create(Column column);

    /**
     *
     * @param column
     * @return
     */
    Column get(String column);
}
