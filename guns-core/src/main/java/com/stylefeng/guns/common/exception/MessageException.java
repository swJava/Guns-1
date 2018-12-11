package com.stylefeng.guns.common.exception;

/**
 * Created by HH on 2017/6/7.
 */
public interface MessageException {
    /**
     * 消息编码
     *
     * @return
     */
    String getMessageCode();

    /**
     * 消息值
     *
     * @return
     */
    String[] getMessageArgs();
}
