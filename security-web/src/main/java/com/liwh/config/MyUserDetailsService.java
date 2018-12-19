package com.liwh.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author: Liwh
 * @ClassName: MyUserDetailsService
 * @Description: 为提供扩展重用，此服务不查询数据库
 * @version: 1.0.0
 * @date: 2018-12-07 11:27 AM
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    //验证数据库用户
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //模拟密码加密
        String password = passwordEncoder.encode("123456");
        logger.info("加密后的数据库密码是：" + password);
        //通过传入的用户名进行数据库查询用户，然后把信息放入spring提供的User中
        User user = new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        //返回User，spring会按HTTP得配置进行验证，授权
        return user;
    }
}
