package com.stylefeng.guns.modular.contentMGR.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.node.ZTreeNode2nd;
import com.stylefeng.guns.modular.contentMGR.service.IColumnService;
import com.stylefeng.guns.modular.system.dao.ColumnMapper;
import com.stylefeng.guns.modular.system.model.Attachment;
import com.stylefeng.guns.modular.system.model.Column;
import com.stylefeng.guns.modular.system.service.IAttachmentService;
import com.stylefeng.guns.util.CodeKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private ColumnMapper columnMapper;

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
    public List<ZTreeNode2nd> treeList() {
        return columnMapper.columnTreeList();
    }
}
