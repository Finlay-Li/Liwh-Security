package com.liwh.authentication.mobile.config;

import com.liwh.validate.filter.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author: Liwh
 * @ClassName: ValidateCodeSecurityConfig
 * @Description: 验证码相关的配置
 * @version: 1.0.0
 * @date: 2018-12-19 5:58 PM
 */
@Component
public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    ValidateCodeFilter validateCodeFilter;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
//        validateCodeFilter.afterPropertiesSet();
        builder.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
