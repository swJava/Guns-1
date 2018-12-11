package com.stylefeng.guns.rest.modular.pay.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;

/**
 * Created by 罗华.
 */
public class PayRequester extends SimpleRequester {
    private static final long serialVersionUID = -6607145030998116256L;
    /**
     * 支付类型
     */
    private Integer type;


    @Override
    public boolean checkValidate() {
        return false;
    }
}
