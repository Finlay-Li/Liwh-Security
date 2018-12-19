package com.liwh.config;

import com.liwh.properties.SecurityProperties;
import com.liwh.validate.generator.ValidateCodeGenerator;
import com.liwh.validate.generator.DefaultImageCodeGenerator;
import com.liwh.validate.generator.DefaultSmsCodeGenerator;
import com.liwh.validate.sender.DefaultSmsSender;
import com.liwh.validate.sender.SmsSender;
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
public class ValidateCodeConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean(name = "imageValidateCodeGenerator")
    @ConditionalOnMissingBean(name = {"imageValidateCodeGenerator"})
    public ValidateCodeGenerator imageCodeGenerator() {
        DefaultImageCodeGenerator imageCodeGenerator = new DefaultImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean(name = "smsValidateCodeGenerator")
    @ConditionalOnMissingBean(name = {"smsValidateCodeGenerator"})
    public ValidateCodeGenerator SmsCodeGenerator() {
        DefaultSmsCodeGenerator smsCodeGenerator = new DefaultSmsCodeGenerator();
        return smsCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(DefaultSmsSender.class)
    public SmsSender smsSender() {
        DefaultSmsSender smsSender = new DefaultSmsSender();
        return smsSender;
    }
}
