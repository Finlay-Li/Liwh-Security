package com.liwh.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Liwh
 * @ClassName: a
 * @Description:
 * @version: 1.0.0
 * @date: 2019-01-21 10:55 AM
 */
public class TestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //目标方法处理之前调用；返回true则放行；返回false则拦截之后所有。

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //目标方法调用之后调用；可以进行对请求的一些处理
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //请求处理完成以后，页面渲染完成以后；
        //它是一定会执行的，无论是否抛出异常
        //若存在全局处理器，则异常抛不到这里
    }
}
