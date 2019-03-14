package com.liwh.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @author: Lwh
 * @InterfaceName: AuthorizeConfigProvider
 * @Description:
 * @version: 1.0.0
 * @date: 2019-03-14 7:57 PM
 */
public interface AuthorizeConfigProvider {

    /*这里就是对请求URI的权限配置，因此我要使用antMatchers()，通过它去使用权限表达式
     * 而antMatchers()是.authorizeRequests()才可以进行调用的，所以我们要authorizeRequests()的返回值*/
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionConfig);
}
