package com.liwh.security;

import com.liwh.loaduser.LoadUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author: Liwh
 * @ClassName: LiwhLoadUserDetailsService
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-26 5:07 PM
 */
@Component("loadUserDetailsService")
public class LiwhLoadUserDetailsService implements LoadUserDetailsService, SocialUserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("表单加载用户.用户名是：" + s);
        return buildUserDetails(s);
    }

    //此时唯一标识：s == userId 。由social传入
    @Override
    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
        logger.info("Social加载用户.唯一标识是：" + s);
        return buildUserDetails(s);
    }

    private SocialUserDetails buildUserDetails(String s) {

        //模拟密码加密
        String password = passwordEncoder.encode("123456");
        logger.info("加密后的数据库密码是：" + password);

        //通过传入的用户名进行数据库查询用户，然后把信息放入spring提供的User中
        SocialUser user = new SocialUser(s, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));

        //返回User，spring会按HTTP得配置进行验证，授权
        return user;
    }

}
