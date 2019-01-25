package com.stylefeng.guns.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Attachment;
import com.stylefeng.guns.modular.system.transfer.AttachmentInfo;

import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/19 15:48
 * @Version 1.0
 */
public interface IAttachmentService extends IService<Attachment> {
    /**
     * 保存附件
     *
     * @param uploadInfo
     * @param masterName
     * @param masterCode
     * @return
     */
    String saveAttachment(AttachmentInfo uploadInfo, String masterName, String masterCode);

    /**
     *
     * @param masterName
     * @param masterCode
     * @return
     */
    List<Attachment> listAttachment(String masterName, String masterCode);

    /**
     * 获取附件信息
     *
     * @param id
     * @return
     */
    Attachment get(Long id);

    /**
     * 更新并移除其他相同类型的附件
     *
     * @param icon
     */
    void updateAndRemoveOther(Attachment icon);
}
