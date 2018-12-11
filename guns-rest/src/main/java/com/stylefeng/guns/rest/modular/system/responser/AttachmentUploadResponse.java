package com.stylefeng.guns.rest.modular.system.responser;

import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import com.stylefeng.guns.rest.modular.member.responser.RegistResponse;

/**
 * Created by 罗华.
 */
public class AttachmentUploadResponse extends SimpleResponser {
    private static final long serialVersionUID = -2747717740531572791L;


    public static Responser me() {
        AttachmentUploadResponse response = new AttachmentUploadResponse();
        response.setCode(SUCCEED);
        return response;
    }
}
