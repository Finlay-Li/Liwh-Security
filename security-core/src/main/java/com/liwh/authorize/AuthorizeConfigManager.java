package com.liwh.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @author: Lwh
 * @interfaceName: AuthorizeConfigManager
 * @Description: 此类用于收集权限的配置并加入security配置中
 * @version: 1.0.0
 * @date: 2019-03-14 8:09 PM
 */
public interface AuthorizeConfigManager {

    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionConfig);
}
