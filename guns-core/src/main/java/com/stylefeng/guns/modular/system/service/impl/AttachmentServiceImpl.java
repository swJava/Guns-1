package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.system.dao.AttachmentMapper;
import com.stylefeng.guns.modular.system.model.Attachment;
import com.stylefeng.guns.modular.system.service.IAttachmentService;
import com.stylefeng.guns.modular.system.transfer.AttachmentInfo;
import com.sun.org.apache.xerces.internal.impl.validation.EntityState;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/19 17:42
 * @Version 1.0
 */
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements IAttachmentService {
    private static final String FILE_NAME_DICT = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBER_RAND_DICT = "0123456789";

    @Value("${application.attachment.store-path}")
    private String storePath = System.getProperty("user.dir");

    @Override
    public void saveAttachment(AttachmentInfo uploadInfo, String masterName, String masterCode) {
        uploadInfo.validate();

        if (null == masterName)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS);

        if (null == masterCode)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS);

        File storeFolder = new File(storePath);

        if (!storeFolder.exists()){
            storeFolder.mkdirs();
        }else if (storeFolder.isFile()){
            storeFolder.mkdirs();
        }
        Iterator<Map<String, Object>> uploadIterator = uploadInfo.iterator();

        while(uploadIterator.hasNext()){
            Map<String, Object> uploadAttachment = uploadIterator.next();
            String filename = getFilename();
            File destFile = new File(storeFolder, filename);
            try {
                FileCopyUtils.copy((byte[]) uploadAttachment.get("content"), destFile);
            } catch (IOException e) {
                continue;
            }

            // 保存附件信息
            Attachment attachment = new Attachment();
            attachment.setFileName((String) uploadAttachment.get("orgName"));
            attachment.setAttachmentName(filename);
            attachment.setSize(Long.parseLong(String.valueOf(uploadAttachment.get("size"))));
            attachment.setStatus(GenericState.Valid.code);
            attachment.setMasterCode(masterCode);
            attachment.setMasterName(masterName);
            attachment.setPath(destFile.getAbsolutePath());
            attachment.setType((String) uploadAttachment.get("type"));
            insert(attachment);
        }
    }

    private String getFilename() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int number = random.nextInt(FILE_NAME_DICT.length());
            sb.append(FILE_NAME_DICT.charAt(number));
        }
        sb.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));

        for (int i = 0; i < 7; i++) {
            int number = random.nextInt(NUMBER_RAND_DICT.length());
            sb.append(NUMBER_RAND_DICT.charAt(number));
        }

        return sb.toString();
    }
}
