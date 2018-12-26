package com.liwh.config;

import com.liwh.loaduser.DefaultLoadUserDetailsService;
import com.liwh.loaduser.LoadUserDetailsService;
import com.liwh.properties.SecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
//    @ConditionalOnMissingBean(name = "loadUserDetailsService")
    //那么扩展还的知道你所配置的条件，怎么样才通用呢————使用类的形式，直接指定Default的类?
    //不行。是不存在这个类，不同的名字就是不同的类；这在定义普通bean时才有用，定义接口的实现不行
    //对啊！name 直接定义成接口名就行了！这样扩展就不需要知道你所配置的条件了！
    @ConditionalOnMissingBean(name = "loadUserDetailsService")
    public LoadUserDetailsService loadUserDetailsService() {
        DefaultLoadUserDetailsService defaultLoad = new DefaultLoadUserDetailsService();
        return defaultLoad;
    }
}
