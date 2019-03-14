package com.liwh.config;

import com.liwh.authentication.mobile.config.AbstractChannelSecurityConfig;
import com.liwh.authentication.mobile.config.SmsAuthenticationSecurityConfig;
import com.liwh.authentication.mobile.config.ValidateCodeSecurityConfig;
import com.liwh.constants.SecurityConstants;
import com.liwh.logout.DefaultLogoutHandler;
import com.liwh.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author: Liwh
 * @ClassName: WebSecurityConfig
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-07 11:15 AM
 */
@Configuration
public class WebSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;
    @Autowired
    private LogoutSuccessHandler defaultLogoutHandler;

    @Bean//持久化的TokenRepository
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //开启：启动服务时，spring创建表
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Bean
    @ConditionalOnMissingBean(DefaultLogoutHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler() {
        DefaultLogoutHandler defaultLogoutHandler = new DefaultLogoutHandler();
        return defaultLogoutHandler;
    }

    //重写web http security 配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);

        http.apply(smsAuthenticationSecurityConfig)
                .and()
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(springSocialConfigurer)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getWebSecurity().getRememberMeSeconds())
                //回调userDetails，进行记住后操作
                .userDetailsService(userDetailsService)
                .and()
                .sessionManagement()
                .invalidSessionUrl(SecurityConstants.DEFAULT_SESSION_INVALID_URL)
                .maximumSessions(1)
//                .expiredSessionStrategy() 并发session时，我们的自定义策略
                .and()
                .and()
                .logout()
                .logoutUrl(securityProperties.getWebSecurity().getSignOutUrl())
                .logoutSuccessHandler(defaultLogoutHandler)
                .deleteCookies("JSESSIONID")//清除session
//                .clearAuthentication(true)//清除Authentication
                .and()
                .authorizeRequests()
                //匹配器放过测试的controller请求 + 首页访问 == 匿名访问
//                .antMatchers("/default-security.html").permitAll()
                .antMatchers(SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL, SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE
                        , securityProperties.getWebSecurity().getLoginPage()
                        , SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + SecurityConstants.DEFAULT_WILDCARD_URL
                        , SecurityConstants.DEFAULT_SESSION_INVALID_URL
                        , securityProperties.getWebSecurity().getSignOutUrl()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //开启跨域
                .cors()
                .and()
                //关闭csrf跨站攻击保护
                .csrf().disable();
    }
}
