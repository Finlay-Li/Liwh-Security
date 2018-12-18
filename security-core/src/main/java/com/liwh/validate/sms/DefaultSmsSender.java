package com.liwh.validate.sms;

import org.springframework.stereotype.Component;

/**
 * @author: Liwh
 * @ClassName: DefaultSmsSender
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-17 5:22 PM
 */
@Component
public class DefaultSmsSender implements SmsSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("默认短信发送，可配置短信商！手机号是：" + mobile + "验证码是：" + code);
    }
}
