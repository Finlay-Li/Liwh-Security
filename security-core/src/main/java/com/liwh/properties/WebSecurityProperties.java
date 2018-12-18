package com.liwh.properties;

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

    //默认值
    private String loginPage = "/default-security.html";
    private String imageLoginUri = "/authentication/form";
    private String smsLoginUri = "/authentication/mobile";
    //记住我的时间
    private int rememberMeSeconds = 3600;
    private LoginHandleReturnType handleReturnType = LoginHandleReturnType.JSON;
}
