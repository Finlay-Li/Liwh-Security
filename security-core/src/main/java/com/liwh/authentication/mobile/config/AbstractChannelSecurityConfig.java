package com.liwh.authentication.mobile.config;

import com.liwh.constants.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author: Liwh
 * @ClassName: AbstractChannelSecurityConfig
 * @Description: authenticationConfig chain 用户名，密码登录相关http配置
 * @version: 1.0.0
 * @date: 2018-12-19 5:44 PM
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationSuccessHandler defaultAuthenticationSuccessHandle;
    @Autowired
    AuthenticationFailureHandler defaultAuthenticationFailureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                //登录处理的请求
                .loginPage(SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL)
                //自定义表单的请求，使用UsernamePasswordAuthenticationFilter进行处理
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(defaultAuthenticationSuccessHandle)
                .failureHandler(defaultAuthenticationFailureHandler);
    }
}

