package com.liwh.authentication;

import com.alibaba.fastjson.JSON;
import com.liwh.enums.LoginHandleReturnType;
import com.liwh.properties.SecurityProperties;
import com.liwh.properties.WebSecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Liwh
 * @ClassName: DefultAuthentication
 * @Description: 登录成功处理器
 * @version: 1.0.0
 * @date: 2018-12-10 8:13 PM
 */
@Component("defaultAuthenticationSuccessHandle")
public class DefaultAuthenticationSuccessHandle extends SavedRequestAwareAuthenticationSuccessHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功！");

        if (securityProperties.getWebSecurity().getHandleReturnType().equals(LoginHandleReturnType.JSON)) {

            //发送积分
            //然后是否能访问到资源呢？答案是不行.实际中是：返回成功的json，进入首页
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(JSON.toJSONString(authentication));
        } else {
            //调父类的重定向
            super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
        }

    }
}
