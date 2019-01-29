package com.liwh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
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
    public TokenStore redisTokenStore() {
        /*Token形式的并发登录，需要从这里下手吗？不！肯定不对！
        * 正确说，和token没关系，token只是访问资源的凭证！和并发登录又有什么关系呢？！
        * */
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        return redisTokenStore;
    }
}
