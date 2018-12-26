package com.liwh.loaduser;

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
//@Component
public class DefaultLoadUserDetailsService implements LoadUserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //验证数据库用户
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.warn("现在使用默认登录验证实现类:请实现LoadUserDetailsService接口完成自己的用户登录验证");

        return null;
    }
}
