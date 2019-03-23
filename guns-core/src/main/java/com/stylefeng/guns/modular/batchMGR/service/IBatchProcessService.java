package com.stylefeng.guns.modular.batchMGR.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.BatchProcess;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/18 00:33
 * @Version 1.0
 */
public interface IBatchProcessService extends IService<BatchProcess> {
    /**
     * 新建 批量处理任务
     * @param batchProcess
     */
    void doCreate(BatchProcess batchProcess);

    /**
     * 更新 批量处理任务
     * @param batchProcess
     */
    void doUpdate(BatchProcess batchProcess);
}
