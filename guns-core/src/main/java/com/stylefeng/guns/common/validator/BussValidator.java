package com.stylefeng.guns.common.validator;

import com.stylefeng.guns.common.exception.ServiceException;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/20 10:49
 * @Version 1.0
 */
public interface BussValidator {
    /**
     * 校验
     *
     * @throws ServiceException
     */
    void doValidate() throws ServiceException;
}
