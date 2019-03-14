package com.liwh.authorize;

import com.liwh.constants.SecurityConstants;
import com.liwh.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author: Lwh
 * @ClassName: DefaultAuthorizeConfigProvider
 * @Description:
 * @version: 1.0.0
 * @date: 2019-03-14 8:02 PM
 */
@Component
public class DefaultAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionConfig) {
        //默认的权限配置，如匿名访问的权限配置
        expressionConfig.antMatchers(SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL, SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE
                , securityProperties.getWebSecurity().getLoginPage()
                , SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + SecurityConstants.DEFAULT_WILDCARD_URL
                , SecurityConstants.DEFAULT_SESSION_INVALID_URL
                , securityProperties.getWebSecurity().getSignOutUrl())
                .permitAll();

    }
}
