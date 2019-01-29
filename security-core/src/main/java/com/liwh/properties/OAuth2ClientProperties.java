package com.liwh.properties;

import lombok.Data;

/**
 * @author: Liwh
 * @ClassName: OAuth2ClientProperties
 * @Description:
 * @version: 1.0.0
 * @date: 2019-01-29 11:00 PM
 */
@Data
public class OAuth2ClientProperties {
   private String clientId;
   private String secret;
   private Integer accessTokenValiditySeconds = 7200;
}
