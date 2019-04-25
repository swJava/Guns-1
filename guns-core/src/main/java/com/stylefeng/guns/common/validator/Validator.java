package com.stylefeng.guns.common.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/20 10:49
 * @Version 1.0
 */
@Target({ METHOD })
@Retention(RUNTIME)
@Documented
public @interface Validator {
    Class<? extends BussValidator>[] chain() default {EmptyValidator.class};
}
