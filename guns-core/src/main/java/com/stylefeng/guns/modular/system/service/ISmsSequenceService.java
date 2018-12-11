package com.stylefeng.guns.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.SmsSequence;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/12 17:19
 * @Version 1.0
 */
public interface ISmsSequenceService extends IService<SmsSequence> {
    /**
     * 加入短信发送队列
     *
     * @param type
     * @param number
     * @return 队列ID
     */
    Long addSmsCaptchSequence(String number);

    /**
     * 更新发送结果
     *
     * @param sequence
     */
    void updateSentResult(SmsSequence sequence);
}
