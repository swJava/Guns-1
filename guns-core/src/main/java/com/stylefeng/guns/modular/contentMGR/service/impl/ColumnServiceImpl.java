package com.stylefeng.guns.modular.contentMGR.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.contentMGR.service.IColumnService;
import com.stylefeng.guns.modular.system.dao.ColumnMapper;
import com.stylefeng.guns.modular.system.model.Column;
import org.springframework.stereotype.Service;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/26 19:50
 * @Version 1.0
 */
@Service
public class ColumnServiceImpl extends ServiceImpl<ColumnMapper, Column> implements IColumnService {
}
