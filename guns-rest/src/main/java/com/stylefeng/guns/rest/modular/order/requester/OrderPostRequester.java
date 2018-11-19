package com.stylefeng.guns.rest.modular.order.requester;

import com.stylefeng.guns.rest.core.SimpleRequester;

/**
 * Created by 罗华.
 */
public class OrderPostRequester extends SimpleRequester {
    private static final long serialVersionUID = -4959430136773709060L;

    @Override
    public boolean checkValidate() {
        return false;
    }
}
