package com.stylefeng.guns.rest.modular.system.controller;

import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.system.model.Attachment;
import com.stylefeng.guns.modular.system.service.IAttachmentService;
import com.stylefeng.guns.modular.system.transfer.AttachmentInfo;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.modular.auth.filter.AuthFilter;
import com.stylefeng.guns.rest.modular.system.responser.AttachmentUploadResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 罗华.
 */
@Api(tags = "系统接口")
@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    private static final Logger log = LoggerFactory.getLogger(AttachmentController.class);

    @Autowired
    private IAttachmentService attachmentService;

    @RequestMapping(value = "/upload" ,method = RequestMethod.POST)
    @ApiOperation(value = "附件上传", response = AttachmentUploadResponse.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "files", value = "上传文件", required = true, dataType = "MultipartFile"),
        @ApiImplicitParam(name = "masterCode", value = "所属主体ID", required = true, dataType = "String", example = "18580255110"),
        @ApiImplicitParam(name = "masterName", value = "文件类别", required = true, dataType = "String", example = "Member_Avatar")
    })
    public Responser upload(
            MultipartFile[] files,
            String masterCode,
            String masterName
    ){
        AttachmentInfo uploadInfo = AttachmentInfo.fromUpload(files);

        if (null == uploadInfo)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"上传附件"});

        String ids = attachmentService.saveAttachment(uploadInfo, masterName, masterCode);

        return AttachmentUploadResponse.me(ids);
    }


    @RequestMapping("/download/{id}")
    public void download(@PathVariable("id") Long id, HttpServletResponse response) {
        File zipFile = null;

        Attachment attachment = attachmentService.selectById(id);

        if (null == attachment)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS);

        zipFile = new File(attachment.getPath());
        try {
            // 输出文件
            FileInputStream in = new FileInputStream(zipFile);
            response.reset();
            response.setContentType("bin");
            response.addHeader("Content-Disposition",
                    "attachment; filename=\"" + new String(URLEncoder.encode(attachment.getFileName(), "UTF-8")) + "\"");
            byte[] buffer = new byte[1024];
            int byteread = 0;
            while ((byteread = in.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, byteread);
            }
            in.close();
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }
}
