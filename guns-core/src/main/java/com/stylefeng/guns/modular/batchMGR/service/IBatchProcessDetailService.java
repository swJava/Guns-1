package com.stylefeng.guns.modular.batchMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.BatchProcessDetail;

import java.util.List;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/18 00:37
 * @Version 1.0
 */
public interface IBatchProcessDetailService extends IService<BatchProcessDetail> {
    /**
     * 查询批量操作明细
     *
     * @param batchCode
     * @return
     */
    List<BatchProcessDetail> selectList(String batchCode);

    /**
     * 保存明细
     *
     * @param processDetail
     */
    void doCreate(BatchProcessDetail processDetail);

    /**
     * 批量新增
     *
     * @param processDetailList
     */
    void doBatchCreate(List<BatchProcessDetail> processDetailList);
}
