package com.liwh.config;

import com.liwh.jwt.DefaultJwtTokenEnhancer;
import com.liwh.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author: Liwh
 * @ClassName: TokenStoreConfig
 * @Description:
 * @version: 1.0.0
 * @date: 2019-01-29 11:43 PM
 */
@Configuration
public class TokenStoreConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    @ConditionalOnProperty(prefix = "liwh.security.oauth2", name = "storeType", havingValue = "redis")
    public TokenStore redisTokenStore() {
        /*Token形式的并发登录，需要从这里下手吗？不！肯定不对！
         * 正确说，和token没关系，token只是访问资源的凭证！和并发登录又有什么关系呢？！
         * */
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        return redisTokenStore;
    }

    @Configuration
    @ConditionalOnProperty(prefix = "liwh.security.oauth2", name = "storeType", havingValue = "jwt", matchIfMissing = true)
    static public class JwtTokenConfig {

        @Autowired
        private SecurityProperties securityProperties;

        @Bean
        public TokenStore jwtTokenStore() {
            JwtAccessTokenConverter converter = jwtAccessTokenConverter();
            JwtTokenStore jwtTokenStore = new JwtTokenStore(converter);
            return jwtTokenStore;
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            String jwtSigningKey = securityProperties.getOauth2().getJwtSigningKey();
            if (StringUtils.isNoneBlank(jwtSigningKey)) {
                converter.setSigningKey(jwtSigningKey);
            }
            return converter;
        }

        @Bean
        @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
        public TokenEnhancer jwtTokenEnhancer() {
            DefaultJwtTokenEnhancer tokenEnhancer = new DefaultJwtTokenEnhancer();
            return tokenEnhancer;
        }
    }

}
