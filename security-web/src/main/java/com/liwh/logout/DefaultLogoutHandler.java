package com.liwh.logout;

import com.alibaba.fastjson.JSON;
import com.liwh.domain.SimpleRespone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Liwh
 * @ClassName: DefaultLogoutHandler
 * @Description:
 * @version: 1.0.0
 * @date: 2019-01-23 10:26 PM
 */
public class DefaultLogoutHandler implements LogoutSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //前段异步请求【退出】........
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        //退出自定义处理一些逻辑 .......
        //响应Json数据
        if (httpServletRequest.getRequestURI().endsWith(".html")) {
            logger.error("don't support html,please implements LogoutSuccessHandler");
            return;
        }
        httpServletResponse.setContentType("application/json;charset=utf-8");
        SimpleRespone response = new SimpleRespone(8008, "退出成功！");
        String string = JSON.toJSONString(response);
        httpServletResponse.getWriter().write(string);
    }
}
