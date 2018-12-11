package com.liwh.config;

import com.liwh.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author: Liwh
 * @ClassName: WebSecurityConfig
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-07 11:15 AM
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired//用我们的实现类
    private AuthenticationSuccessHandler defaultAuthenticationSuccessHandle;
    @Autowired
    private AuthenticationFailureHandler defaultAuthenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    //重写web http security 配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //访问自定义首页
                .loginPage("/default-security.html")  /*html请求*/
//                .loginPage("/authentication/require")  /*资源访问请求*/
                //自定义的表单请求，使用UsernamePasswordFilter进行处理
                .loginProcessingUrl("/authentication/form")
                //加入我们的成功处理器
                .successHandler(defaultAuthenticationSuccessHandle)
                .failureHandler(defaultAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                //匹配器放过测试的controller请求 + 首页访问
//                .antMatchers("/default-security.html").permitAll()
                .antMatchers("/authentication/require", securityProperties.getWebProperties().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //关闭csrf跨站攻击保护
                .csrf().disable();
    }
}
