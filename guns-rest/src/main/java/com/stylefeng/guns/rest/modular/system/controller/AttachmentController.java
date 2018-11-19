package com.stylefeng.guns.rest.modular.system.controller;

import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.modular.system.responser.AttachmentUploadResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 罗华.
 */
@Api(tags = "附件接口")
@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @RequestMapping(value = "/upload" ,method = RequestMethod.POST)
    @ApiOperation(value = "附件上传", response = AttachmentUploadResponse.class)
    public Responser upload(
            String masterCode,
            String masterName
    ){

        return null;
    }
}
