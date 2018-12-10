package com.liwh.properties;

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
    private String LoginPage = "/default-security.html";
}
