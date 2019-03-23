package com.stylefeng.guns.modular.batchMGR.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.batchMGR.service.IBatchProcessDetailService;
import com.stylefeng.guns.modular.system.dao.BatchProcessDetailMapper;
import com.stylefeng.guns.modular.system.model.BatchProcessDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/18 00:38
 * @Version 1.0
 */
@Service
public class BatchProcessDetailServiceImpl extends ServiceImpl<BatchProcessDetailMapper, BatchProcessDetail> implements IBatchProcessDetailService {
    private static final Logger log = LoggerFactory.getLogger(BatchProcessDetailServiceImpl.class);

    @Override
    public List<BatchProcessDetail> selectList(String batchCode) {
        return null;
    }

    @Override
    public void doCreate(BatchProcessDetail processDetail) {

    }

    @Override
    public void doBatchCreate(List<BatchProcessDetail> processDetailList) {
        insertBatch(processDetailList);
    }
}
