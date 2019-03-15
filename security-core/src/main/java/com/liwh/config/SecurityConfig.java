package com.liwh.config;

import com.liwh.loaduser.DefaultLoadUserDetailsService;
import com.liwh.loaduser.LoadUserDetailsService;
import com.liwh.properties.SecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

/**
 * @author: Liwh
 * @ClassName: SecurityConfig
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-10 11:15 AM
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig {

    @Bean
    //我们指定的是name。name扩展实现就得叫这个名字。否则DefaultLoadUserDetailsService依旧会创建
    @ConditionalOnMissingBean(name = "loadUserDetailsService")
    public LoadUserDetailsService loadUserDetailsService() {
        DefaultLoadUserDetailsService defaultLoad = new DefaultLoadUserDetailsService();
        return defaultLoad;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    /*OAuth2 的表达式处理器，默认是null
    所以我们要自定义，并加入spring 容器中，否则无法解析access()*/
    @Bean
    public OAuth2WebSecurityExpressionHandler auth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }

}
