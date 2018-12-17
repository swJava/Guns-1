package com.stylefeng.guns.rest.modular.education.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/18 00:30
 * @Version 1.0
 */
public class AdjustQueryRequester extends SimpleRequester {

    private String serviceType;

    private String sourceCode;

    private String destCode;
    

    @Override
    public boolean checkValidate() {
        return false;
    }
}
