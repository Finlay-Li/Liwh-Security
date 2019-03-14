package com.liwh.authorize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author: Lwh
 * @ClassName: DefaultAuthorizeConfigManager
 * @Description:
 * @version: 1.0.0
 * @date: 2019-03-14 8:11 PM
 */
@Component
public class DefaultAuthorizeConfigManager implements AuthorizeConfigManager {

    //收集权限配置的provider
    @Autowired
    private Set<AuthorizeConfigProvider> authorizeConfigProviders;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionConfig) {
        if (authorizeConfigProviders == null) {
            logger.warn("authorizeConfigProviders is null");
            return;
        }
        for (AuthorizeConfigProvider provider : authorizeConfigProviders) {
            //把provider的配置拿给manager的expressionConfig
            /*逻辑是：
            * 1 启动spring 执行security的配置
            * 2 走到manager的追加
            * 3 manager的expressionConfig丢给provide
            * 4 执行provide的config()
            * 5 把权限配置都添加上了*/
            provider.config(expressionConfig);
        }
        //除开配置的，其他都要权限
        expressionConfig
                .anyRequest()
                .authenticated();
    }
}
