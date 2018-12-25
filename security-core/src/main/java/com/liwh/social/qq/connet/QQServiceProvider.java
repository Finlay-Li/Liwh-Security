package com.liwh.social.qq.connet;

import com.liwh.social.qq.api.service.QQ;
import com.liwh.social.qq.api.service.impl.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author: Liwh
 * @ClassName: QQServiceProvide
 * @Description: QQServiceProvider
 * @version: 1.0.0
 * @date: 2018-12-25 3:54 PM
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
    String appId;
    private static final String URL_AUTHORIZE_CODE = "https://graph.qq.com/oauth2.0/authorize";
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE_CODE, URL_ACCESS_TOKEN));
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
