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

    protected String[] arguments = new String[0];

    public ServiceException(){}

    public ServiceException(String code){
        this.messageCode = code;
    }

    public ServiceException(String code, String[] arguments){
        this.messageCode = code;
        String[] newArguments = new String[arguments.length];
        System.arraycopy(arguments, 0, newArguments, 0, arguments.length);

        this.arguments = newArguments;
    }

    @Override
    public String getMessageCode() {
        return this.messageCode;
    }

    public void addArguments(String argument) {
        String[] newArguments = new String[arguments.length + 1];

        System.arraycopy(arguments, 0, newArguments, 0, arguments.length);

        newArguments[arguments.length] = argument;

        this.arguments = newArguments;
    }

    @Override
    public String[] getMessageArgs() {
        return this.arguments;
    }
}
