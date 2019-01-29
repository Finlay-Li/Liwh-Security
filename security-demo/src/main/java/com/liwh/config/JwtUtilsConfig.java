package com.liwh.config;

import com.liwh.properties.SecurityProperties;
import com.liwh.service.AdminService;
import com.liwh.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: QIN GAOPAN
 * @ClassName: AppConfig
 * @Description:
 * @Version: V1.0.0
 * @Date: 2018/11/09 15:55
 */
@Configuration
public class JwtUtilsConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AdminService adminService;


    @Bean
    public JwtUtils jwtUtils() {
        JwtUtils jwtUtils = new JwtUtils(securityProperties);
        jwtUtils.setAdminService(adminService);
        return jwtUtils;
    }


}
