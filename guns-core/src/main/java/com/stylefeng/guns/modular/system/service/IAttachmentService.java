package com.stylefeng.guns.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Attachment;
import com.stylefeng.guns.modular.system.transfer.AttachmentInfo;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/19 15:48
 * @Version 1.0
 */
public interface IAttachmentService extends IService<Attachment> {

    void saveAttachment(AttachmentInfo uploadInfo, String masterName, String masterCode);
}
