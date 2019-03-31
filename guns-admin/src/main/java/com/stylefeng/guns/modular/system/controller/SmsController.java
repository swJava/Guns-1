package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.Tip;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.studentMGR.controller.StudentController;
import com.stylefeng.guns.modular.system.service.ISmsSequenceService;
import com.stylefeng.guns.util.RegexUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 罗华.
 */
@RestController
@RequestMapping("/sms/")
public class SmsController  extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    private ISmsSequenceService smsSequenceService;

    @RequestMapping(value = "/captcha/send", method = {RequestMethod.POST})
    public Tip sendCaptcha(String number){

        if (null == number)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS);

        if (!RegexUtil.isMobile(number))
            throw new ServiceException(MessageConstant.MessageCode.SYS_DATA_ILLEGAL);

        Long queueId = smsSequenceService.addSmsCaptchSequence(number);

        log.info("captcha sms succeed join sequence");
        return SUCCESS_TIP;
    }
}
