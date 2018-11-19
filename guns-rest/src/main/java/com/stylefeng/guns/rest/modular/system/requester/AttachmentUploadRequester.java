package com.stylefeng.guns.rest.modular.system.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;

/**
 * Created by 罗华.
 */
public class AttachmentUploadRequester extends SimpleRequester {
    private static final long serialVersionUID = 4259679329913032708L;

    private String masterCode;

    private String masterName;


    @Override
    public boolean checkValidate() {
        return false;
    }
}
