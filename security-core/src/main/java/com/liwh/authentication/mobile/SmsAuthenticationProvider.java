package com.liwh.authentication.mobile;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author: Liwh
 * @ClassName: SmsAuthenticationProvider
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-18 3:06 PM
 */
@Data
public class SmsAuthenticationProvider implements AuthenticationProvider {

    UserDetailsService userDetailsService;

    /**
     * 组装已认证的Authentication
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken smsAuthenticationToken = null;
        if (authentication instanceof SmsAuthenticationToken) {
            smsAuthenticationToken = (SmsAuthenticationToken) authentication;
        } else {
            throw new InternalAuthenticationServiceException("不是SmsAuthenticationToken：" + authentication.getClass().getSimpleName());
        }
        //通过UserDetailsService去数据库中获取User
        UserDetails dbUser = userDetailsService.loadUserByUsername((String) smsAuthenticationToken.getPrincipal());
        //如果为null
        if (dbUser == null) {
            throw new InternalAuthenticationServiceException("该手机号无法获取用户信息：" + (String) smsAuthenticationToken.getPrincipal());
        }
        //重新new 一个已认证权限的Token
        SmsAuthenticationToken result = new SmsAuthenticationToken(dbUser, dbUser.getAuthorities());
        //把UserDetails放入Token
        result.setDetails(smsAuthenticationToken.getDetails());
        //返回已认证的Authentication
        return result;
    }

    /**
     * 判断是否支持Token
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        boolean assignable = SmsAuthenticationToken.class.isAssignableFrom(authentication);
        return assignable;
    }
}
