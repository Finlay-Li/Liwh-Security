package com.liwh.validate.sms;

/**
 * @author: Liwh
 * @interfaceName: SmsSender
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-17 4:37 PM
 */
public interface SmsSender {
    void send(String mobile, String code);
}
