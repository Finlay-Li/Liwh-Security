package com.liwh.validate.sms;

import com.liwh.properties.SecurityProperties;
import com.liwh.validate.code.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * @author: Liwh
 * @ClassName: DefaultSmsCodeGenerator
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-17 4:47 PM
 */
@Data
public class DefaultSmsCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public SmsCode generate(ServletWebRequest servletWebRequest) {

        String code = RandomStringUtils.randomNumeric(securityProperties.getValidateCode().getSmsCode().getLength());
        int expireTime = securityProperties.getValidateCode().getSmsCode().getExpireTime();
        return new SmsCode(code, expireTime);
    }
}
