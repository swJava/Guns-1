package com.stylefeng.guns.common.exception;

/**
 * 服务层异常
 *
 * 需要由Controller层捕获、并处理的
 *
 * Created by HH on 2017/6/6.
 */
public class ServiceException extends RuntimeException implements MessageException {

    private static final long serialVersionUID = -8731795258389020676L;

    protected String messageCode;
    /**
     * 异常网元
     *
     * 一般是IP地址
     *
     */
    private String exceptionDomain;

    public ServiceException(){}

    public ServiceException(String code){
        this.messageCode = code;
    }

    public String getExceptionDomain() {
        return exceptionDomain;
    }

    public void setExceptionDomain(String exceptionDomain) {
        this.exceptionDomain = exceptionDomain;
    }

    @Override
    public String getMessageCode() {
        return this.messageCode;
    }

    @Override
    public String[] getMessageArgs() {
        String msg = getMessage();

        if (null != msg)
            return new String[]{this.exceptionDomain, msg};
        else
            return new String[]{this.exceptionDomain};
    }
}
