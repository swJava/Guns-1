package com.stylefeng.guns.util;

import com.stylefeng.guns.config.properties.ApplicationProperties;

/**
 * 验证码工具类
 */
public class KaptchaUtil {

    /**
     * 获取验证码开关
     */
    public static Boolean getKaptchaOnOff() {
        return SpringContextHolder.getBean(ApplicationProperties.class).getKaptchaOpen();
    }
}