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
    private LoginHandleReturnType handleReturnType =  LoginHandleReturnType.JSON;
}
