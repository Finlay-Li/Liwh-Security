package com.liwh.security;

import com.liwh.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author: Lwh
 * @ClassName: LiwhAuthorizeConfigProvider
 * @Description:
 * @version: 1.0.0
 * @date: 2019-03-14 8:06 PM
 */
@Component
@Order(Integer.MAX_VALUE)
public class LiwhAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionConfig) {
        expressionConfig.anyRequest().access("@RBACService.hasPermission(request,authentication)");
    }
}
