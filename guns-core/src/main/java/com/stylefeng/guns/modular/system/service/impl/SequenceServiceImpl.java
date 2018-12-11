package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.dao.SequenceMapper;
import com.stylefeng.guns.modular.system.model.Sequence;
import com.stylefeng.guns.modular.system.service.ISequenceService;
import org.springframework.stereotype.Service;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/25 20:31
 * @Version 1.0
 */
@Service("sequenceService")
public class SequenceServiceImpl extends ServiceImpl<SequenceMapper, Sequence> implements ISequenceService {
}
