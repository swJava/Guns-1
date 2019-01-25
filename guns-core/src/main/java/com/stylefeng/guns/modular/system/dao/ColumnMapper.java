package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.core.node.ZTreeNode2nd;
import com.stylefeng.guns.modular.system.model.Column;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 栏目 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-26
 */
public interface ColumnMapper extends BaseMapper<Column> {
    /**
     * 目录树
     *
     * ZTree 简单数据
     * @return
     */
    List<ZTreeNode2nd> columnTreeList();
}
