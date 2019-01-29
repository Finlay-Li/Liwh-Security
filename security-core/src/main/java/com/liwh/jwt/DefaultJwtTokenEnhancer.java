package com.liwh.jwt;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;

/**
 * @author: Liwh
 * @ClassName: DefaultJwtTokenEnhancer
 * @Description:
 * @version: 1.0.0
 * @date: 2019-01-30 1:53 AM
 */
public class DefaultJwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        HashMap<String, Object> info = new HashMap<>();
        info.put("company", "dodou");
        if (oAuth2AccessToken instanceof DefaultOAuth2AccessToken) {
            ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        }
        return oAuth2AccessToken;
    }
}
