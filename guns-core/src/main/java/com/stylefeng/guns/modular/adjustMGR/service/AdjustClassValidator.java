package com.stylefeng.guns.modular.adjustMGR.service;

import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.common.validator.BussValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/20 10:52
 * @Version 1.0
 */
public class AdjustClassValidator implements BussValidator {
    @Override
    public void doValidate() throws ServiceException {

    }
}
