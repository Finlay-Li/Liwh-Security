package com.liwh.social.qq.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.liwh.social.qq.api.model.QQUserInfo;
import com.liwh.social.qq.api.service.QQ;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.web.client.RestTemplate;

/**
 * @author: Liwh
 * @ClassName: QQimpl
 * @Description: 第1步，通过继承API，去拿到用户数据
 * @version: 1.0.0
 * @date: 2018-12-25 11:22 AM
 */
/*
 * 1、该实现类的实例必须是多例的，因为每一个用户的String accessToken都不同
 *    而这是成员变量，一个实例就应该有一个accessToken
 *    否则，每次去获取用户数据时，accessToken将会变成共享变量，将会引发线程安全问题！
 *
 * 2、怎么做到多例呢？还不简单，使用方式改为我们自己new。不加入spring就好了
 * */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    Logger logger = LoggerFactory.getLogger(getClass());

    private String appId;
    private String openId;
    //获取用户唯一标识
    private final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    //access_token我们不处理，交给父类来处理；但是要指定access_token的处理策略——>挂在请求参数上，即请求行
    private final String URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    //当其他地方要调getQQUserInfo（）时，就进行初始化工作
    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        RestTemplate restTemplate = getRestTemplate();
        String url = String.format(URL_GET_OPENID, accessToken);
        String result = restTemplate.getForObject(url, String.class);
        String s = StringUtils.substringBetween(result, "\"openid\":", "}");
        this.openId = s;
    }

    @Override
    public QQUserInfo getQQUserInfo() {
        //发请求,得到Json数据
        String url = String.format(URL_GET_USER_INFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);

        //转换为实体类
        QQUserInfo parse = (QQUserInfo) JSON.parse(result);
        parse.setOpenId(openId);

        logger.info("获取用户信息成功：" + parse.toString());
        return parse;
    }
}
