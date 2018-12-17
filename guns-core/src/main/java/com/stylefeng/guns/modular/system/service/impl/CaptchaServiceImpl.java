package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.dao.CaptchaMapper;
import com.stylefeng.guns.modular.system.model.Captcha;
import com.stylefeng.guns.modular.system.service.ICaptchaService;
import com.stylefeng.guns.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.net.httpserver.AuthFilter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/15 14:45
 * @Version 1.0
 */
@Service
public class CaptchaServiceImpl extends ServiceImpl<CaptchaMapper, Captcha> implements ICaptchaService {
    private static final Logger log = LoggerFactory.getLogger(CaptchaServiceImpl.class);

    private static final String NUMBER_RAND_DICT = "0123456789";

    @Value("${application.message.captch.length:6}")
    private int captchaLength = 6;

    @Value("${application.message.captch.expire-time:300}")
    private int captchaExpireTime = 300;

    @Autowired
    private CaptchaMapper captchaMapper;

    @Override
    public int expireAllCaptcha(String number) {
        Wrapper<Captcha> queryWrapper = new EntityWrapper<Captcha>();
        Date now = new Date();

        queryWrapper.eq("destaddr", number);
        queryWrapper.eq("status", 1);
        queryWrapper.le("expire_date", now);
        List<Captcha> hisCaptchaList = captchaMapper.selectList(queryWrapper);

        int uptCount = 0;
        for(Captcha captcha : hisCaptchaList){
            captcha.setStatus(-1);
            captcha.setExpireDate(now);

            captchaMapper.updateById(captcha);

            uptCount ++;
        }
        return uptCount;
    }

    @Override
    public void sendComplete(String number) {
        Wrapper<Captcha> queryWrapper = new EntityWrapper<Captcha>();

        queryWrapper.eq("destaddr", number);
        queryWrapper.eq("status", 0);

        List<Captcha> captchaList = captchaMapper.selectList(queryWrapper);
        Date now = DateUtil.add(new Date(), Calendar.HOUR, 8);

        for (Captcha captcha : captchaList){
            captcha.setStatus(1);

            captcha.setExpireDate(DateUtil.add(now, Calendar.SECOND, captchaExpireTime));

            log.debug("Sms send over event: {} expire at {}", captcha.getId(), captcha.getExpireDate());
            captchaMapper.updateById(captcha);
        }
    }

    @Override
    public boolean checkCaptcha(String number, String captcha) {
        if (null == captcha)
            return false;

        if (null == number)
            return false;

        Wrapper<Captcha> queryWrapper = new EntityWrapper<Captcha>();
        queryWrapper.eq("destaddr", number);
        queryWrapper.eq("captcha", captcha);
        queryWrapper.eq("status", 1);  // 有效状态
        queryWrapper.ge("expire_date", new Date());

        int existCount = selectCount(queryWrapper);

        return existCount > 0;
    }

    @Override
    public Captcha createCaptcha(String number) {
        Captcha captchaEntity = new Captcha();

        captchaEntity.setStatus(0); // 新建（未发送）
        captchaEntity.setDestaddr(number);
        captchaEntity.setCaptcha(generateCaptcha(captchaLength));

        captchaMapper.insert(captchaEntity);

        return captchaEntity;
    }


    private String generateCaptcha(int len) {
        Random random = new Random();

        StringBuffer captcha = new StringBuffer();
        for (int i = 0; i < len; i++) {
            int number = random.nextInt(NUMBER_RAND_DICT.length());
            captcha.append(NUMBER_RAND_DICT.charAt(number));
        }

        return captcha.toString();
    }
}
