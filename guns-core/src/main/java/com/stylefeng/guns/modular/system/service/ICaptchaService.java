package com.stylefeng.guns.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Captcha;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/15 14:43
 * @Version 1.0
 */
public interface ICaptchaService extends IService<Captcha> {

    /**
     * 将验证码置失效
     *
     * @param number
     * @return
     */
    int expireAllCaptcha(String number);

    /**
     * 创建验证码
     *
     * @param number
     * @param captcha
     */
    Captcha createCaptcha(String number);

    /**
     * 验证码已发送
     *
     * @param number
     */
    void sendComplete(String number);

    /**
     * 校验验证码
     *
     * @param number
     * @param captcha
     * @return
     */
    boolean checkCaptcha(String number, String captcha);
}
