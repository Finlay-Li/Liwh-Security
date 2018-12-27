package com.liwh.controller;

import com.liwh.domain.SimpleRespone;
import com.liwh.properties.SecurityProperties;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Liwh
 * @ClassName: WebSecurityController
 * @Description: 访问请求引导controller
 * @version: 1.0.0
 * @date: 2018-12-10 11:23 AM
 */
@RestController
public class WebSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private SecurityProperties securityProperties;

    @GetMapping("/authentication/require")
    @ApiOperation(value = "访问应用就跳转到这里")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleRespone requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //1.当security判断完此请求是要认证后，会存储在cache中，我们拿到此访问的请求
        SavedRequest cacheRequest = requestCache.getRequest(request, response);
        //2.若以html结尾：重定向到登录页面
        String redirectUrl = cacheRequest.getRedirectUrl();
        if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) {
            redirectStrategy.sendRedirect(request, response, securityProperties.getWebSecurity().getLoginPage());//重定向到登录页面
        }
        //3.若是其他请求，返回未认证的错误json数据
        return new SimpleRespone("访问的服务需要身份认证，请引导用户到登录页");
    }
}
