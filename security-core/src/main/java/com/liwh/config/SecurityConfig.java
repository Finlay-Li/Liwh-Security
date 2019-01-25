package com.liwh.config;

import com.liwh.loaduser.DefaultLoadUserDetailsService;
import com.liwh.loaduser.LoadUserDetailsService;
import com.liwh.properties.SecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
}
