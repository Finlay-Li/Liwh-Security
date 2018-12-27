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
                //访问自定义首页
//                .loginPage(SecurityConstants.DEFAULT_LOGIN_PAGE_URL)  /*html请求*/
                //把原本的单纯的“页面请求”改成我们的requireController由它去引导请求！只是换了个思路而已！
                .loginPage(SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL)
                //自定义表单的请求，使用UsernamePasswordAuthenticationFilter进行处理
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(defaultAuthenticationSuccessHandle)
                .failureHandler(defaultAuthenticationFailureHandler);
    }
}

