package com.liwh.config;

import com.liwh.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
}
