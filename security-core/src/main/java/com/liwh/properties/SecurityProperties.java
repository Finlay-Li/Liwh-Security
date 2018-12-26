package com.liwh.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Liwh
 * @ClassName: SecurityProperties
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-10 11:12 AM
 */
@Data
@ConfigurationProperties(prefix = "liwh.security")
public class SecurityProperties {

    private WebSecurityProperties webSecurity = new WebSecurityProperties();
    private ValidateCodeProperties validateCode = new ValidateCodeProperties();
    private SocialProperties social = new SocialProperties();
}
