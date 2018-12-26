package com.liwh.social.config;

import com.liwh.properties.SecurityProperties;
import com.liwh.social.qq.connet.factory.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author: Liwh
 * @ClassName: QQAutoConfig
 * @Description: 重写SocialAutoConfigurer，编写我们的ConnectionFactory
 * @version: 1.0.0
 * @date: 2018-12-26 7:09 PM
 */
@Configuration
@ConditionalOnProperty(prefix = "liwh.security.social.qq", name = "app-id")//name是其中的属性
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        String appId = securityProperties.getSocial().getQq().getAppId();
        String appSecret = securityProperties.getSocial().getQq().getAppSecret();
        String providerId = securityProperties.getSocial().getQq().getProviderId();
        QQConnectionFactory qqConnectionFactory = new QQConnectionFactory(providerId, appId, appSecret);
        return qqConnectionFactory;
    }
}
