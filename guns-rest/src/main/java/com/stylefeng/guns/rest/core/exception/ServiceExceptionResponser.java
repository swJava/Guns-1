package com.stylefeng.guns.rest.core.exception;

import com.stylefeng.guns.rest.core.SimpleResponser;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/13 9:57
 * @Version 1.0
 */
public class ServiceExceptionResponser extends SimpleResponser {

    private static final long serialVersionUID = 1L;

    public ServiceExceptionResponser() {
    }

    public ServiceExceptionResponser(String code, String message){

        super.setCode(code);

        super.setMessage(message);
    }
}
