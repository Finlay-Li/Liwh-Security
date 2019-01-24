package com.liwh.properties;

import com.liwh.constants.SecurityConstants;
import com.liwh.enums.LoginHandleReturnType;
import lombok.Data;

/**
 * @author: Liwh
 * @ClassName: WebSecurityProperties
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-10 3:20 PM
 */
@Data
public class WebSecurityProperties {

    //记住我的时间
    private int rememberMeSeconds = 3600;
    //处理登录
    private String SignUpUrl = "/authentication/form";
    //处理退出
    private String SignOutUrl = "/logout";
    //访问登录页面的请求。
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
    private LoginHandleReturnType handleReturnType = LoginHandleReturnType.JSON;
}
