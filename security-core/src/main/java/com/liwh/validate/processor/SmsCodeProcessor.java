package com.liwh.validate.processor;

import com.liwh.validate.sms.SmsCode;
import com.liwh.validate.sms.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: Liwh
 * @ClassName: SmsCodeProcessor
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-17 8:23 PM
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<SmsCode> {
    private final String MOBILE_NAME = "mobile";
    SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private SmsSender smsSender;

    @Override
    void save(ServletWebRequest servletWebRequest, SmsCode validateCode) throws Exception {
        sessionStrategy.setAttribute(servletWebRequest, SESSION_VALIDATA_PREFIX + "SMS", validateCode);
    }

    @Override
    void send(ServletWebRequest servletWebRequest, SmsCode validateCode) throws Exception {
        //获取用户输入的手机号
        String mobile = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), MOBILE_NAME);
        //发送短信
        smsSender.send(mobile, validateCode.getCode());
    }
}
