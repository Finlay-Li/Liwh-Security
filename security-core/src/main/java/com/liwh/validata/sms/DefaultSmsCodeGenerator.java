package com.liwh.validata.sms;

import com.liwh.properties.SecurityProperties;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author: Liwh
 * @ClassName: DefaultSmsCodeGenerator
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-17 4:47 PM
 */
@Data
public class DefaultSmsCodeGenerator implements SmsCodeGenerator {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public SmsCode generator() {

        String code = RandomStringUtils.randomNumeric(securityProperties.getValidataCode().getSmsCode().getLength());
        int expireTime = securityProperties.getValidataCode().getSmsCode().getExpireTime();
        return new SmsCode(code, expireTime);
    }
}
