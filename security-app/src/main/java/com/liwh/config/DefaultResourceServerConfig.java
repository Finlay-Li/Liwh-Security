package com.liwh.config;

import com.liwh.authentication.mobile.config.SmsAuthenticationSecurityConfig;
import com.liwh.authentication.mobile.config.ValidateCodeSecurityConfig;
import com.liwh.authorize.AuthorizeConfigManager;
import com.liwh.constants.SecurityConstants;
import com.liwh.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author: Liwh
 * @ClassName: DefaultResourceServerConfig
 * @Description:
 * @version: 1.0.0
 * @date: 2019-01-25 3:18 PM
 */
@Configuration
@EnableResourceServer
public class DefaultResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    AuthenticationSuccessHandler defaultAuthenticationSuccessHandle;
    @Autowired
    AuthenticationFailureHandler defaultAuthenticationFailureHandler;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;
    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        super.configure(http);Other config

        //登录处理的请求
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL)
                //自定义表单的请求，使用UsernamePasswordAuthenticationFilter进行处理
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(defaultAuthenticationSuccessHandle)
                .failureHandler(defaultAuthenticationFailureHandler);
        http.apply(smsAuthenticationSecurityConfig)
                .and()
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(springSocialConfigurer)
                .and()
                //开启跨域
                .cors()
                .and()
                //关闭csrf跨站攻击保护
                .csrf().disable();
        authorizeConfigManager.config(http.authorizeRequests());
    }
}
