package com.stylefeng.guns.modular.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.modular.system.dao.CaptchaMapper;
import com.stylefeng.guns.modular.system.dao.SmsSequenceMapper;
import com.stylefeng.guns.modular.system.model.Captcha;
import com.stylefeng.guns.modular.system.model.SmsSequence;
import com.stylefeng.guns.modular.system.service.ICaptchaService;
import com.stylefeng.guns.modular.system.service.ISmsSequenceService;
import com.stylefeng.guns.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/14 16:58
 * @Version 1.0
 */
@Service
public class SmsSequenceServiceImpl extends ServiceImpl<SmsSequenceMapper, SmsSequence> implements ISmsSequenceService {

    @Value("${application.message.captch.template:''}")
    private String captchaTemplate;

    @Autowired
    private SmsSequenceMapper smsSequenceMapper;

    @Autowired
    private ICaptchaService captchaService;

    @Override
    public Long addSmsCaptchSequence(String number) {

        if (null == captchaTemplate || captchaTemplate.length() == 0){
            return -1L;
        }
        // 将之前同一组的验证码置失效
        captchaService.expireAllCaptcha(number);
        Captcha captcha = captchaService.createCaptcha(number);
        Date now = new Date();

        SmsSequence smsSequence = new SmsSequence();
        smsSequence.setNumber(number);
        smsSequence.setTemplate(captchaTemplate);

        Map<String, Object> content = new HashMap<String, Object>();
        content.put("code", captcha.getCaptcha());
        smsSequence.setContent(JSON.toJSONString(content));

        smsSequence.setSentStatus(SmsSequence.SentStatusEnum.NoSent.code);
        smsSequence.setCreateDate(new Date());
        smsSequence.setStatus(GenericState.Valid.code);

        smsSequenceMapper.insert(smsSequence);

        return smsSequence.getId();
    }

    @Override
    public void updateSentResult(SmsSequence sequence) {
        captchaService.sendComplete(sequence.getNumber());
        // 更新发送结果
        smsSequenceMapper.updateById(sequence);
    }
}
