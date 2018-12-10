package com.liwh.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Liwh
 * @ClassName: WebSecurityController
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-10 11:23 AM
 */
@RestController
public class WebSecurityController {

    @GetMapping("/authentication/require")
    @ApiOperation(value = "自定义表单登录")
    public void authenticationByForm() {

        //判断是html请求——>重定向到首页（默认与自定义）
        //是资源请求——>错误的json数据提示访问首页进行登录
        System.out.println();
    }
}
