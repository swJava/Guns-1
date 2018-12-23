package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.system.dao.AttachmentMapper;
import com.stylefeng.guns.modular.system.model.Attachment;
import com.stylefeng.guns.modular.system.service.IAttachmentService;
import com.stylefeng.guns.modular.system.transfer.AttachmentInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public String saveAttachment(AttachmentInfo uploadInfo, String masterName, String masterCode) {
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

        StringBuilder savedIds = new StringBuilder();
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

            savedIds.append(attachment.getId()).append(",");
        }

        return savedIds.substring(0, savedIds.length() - 1);
    }

    @Override
    public List<Attachment> listAttachment(String masterName, String masterCode) {
        if (null == masterCode || null == masterName)
            return new ArrayList<>();

        return selectList(new EntityWrapper<Attachment>()
                        .eq("master_code", masterCode)
                        .eq("master_name", masterName)
                        .eq("status", 1)
        );
    }

    @Override
    public Attachment get(Long id) {
        if (null == id)
            return null;

        return selectById(id);
    }

    @Override
    public void updateAndRemoveOther(Attachment newAttachment) {
        Wrapper<Attachment> attachmentWrapper = new EntityWrapper<Attachment>();

        attachmentWrapper.eq("master_name", newAttachment.getMasterName());
        attachmentWrapper.eq("master_code", newAttachment.getMasterCode());
        attachmentWrapper.eq("status", GenericState.Valid.code);

        List<Attachment> existList = selectList(attachmentWrapper);
        for(Attachment attachment : existList){
            attachment.setStatus(GenericState.Invalid.code);
            updateById(attachment);
        }

        updateById(newAttachment);
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
