package com.liwh.config;

import com.liwh.properties.SecurityProperties;
import com.liwh.validata.image.DefultImageCodeGenerator;
import com.liwh.validata.image.ImageCodeGenerator;
import com.liwh.validata.sms.DefaultSmsCodeGenerator;
import com.liwh.validata.sms.SmsCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Liwh
 * @ClassName: ImageCodeConfig
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-16 10:46 AM
 */
@Configuration
public class ValidataCodeConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = {"imageCodeGenerator"})
    public ImageCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new DefultImageCodeGenerator();
        ((DefultImageCodeGenerator) imageCodeGenerator).setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeGenerator.class)
    public SmsCodeGenerator SmsCodeGenerator() {
        SmsCodeGenerator smsCodeGenerator = new DefaultSmsCodeGenerator();
        return smsCodeGenerator;
    }
}
