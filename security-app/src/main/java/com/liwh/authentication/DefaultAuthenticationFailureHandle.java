package com.liwh.authentication;

import com.alibaba.fastjson.JSON;
import com.liwh.enums.LoginHandleReturnType;
import com.liwh.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Liwh
 * @ClassName: DefaultAuthenticationFaiHandle
 * @Description: 登录失败处理器
 * @version: 1.0.0
 * @date: 2018-12-10 8:32 PM
 */
@Component("defaultAuthenticationFailureHandle")
public class DefaultAuthenticationFailureHandle extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        logger.info("登录失败！");
        if (securityProperties.getWebSecurity().getHandleReturnType().equals(LoginHandleReturnType.JSON)) {

            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(JSON.toJSONString(e.getMessage()));
        } else {
            //调父类的重定向
            super.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
        }
    }
}
