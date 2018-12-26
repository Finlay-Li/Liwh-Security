package com.liwh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author: Liwh
 * @ClassName: SocialConfig
 * @Description: JdbcRepostry
 * @version: 1.0.0
 * @date: 2018-12-26 3:09 PM
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {

        /*
         * 1 dataSource
         * 2 connectionFactoryLocator
         * 3 内容加密处理策略：Encryptors
         * */
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());

        //social读取的表名是固定的，但是可以设置前缀
        repository.setTablePrefix("SOCIAL_");
        return repository;
    }

    //声明使用SpringSocialConfigurer，让Social过滤器起作用
    @Bean
    public SpringSocialConfigurer springSocialConfigurer(){
        return new SpringSocialConfigurer();
    }
}
