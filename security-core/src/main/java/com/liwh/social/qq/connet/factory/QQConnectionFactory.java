package com.liwh.social.qq.connet.factory;

import com.liwh.social.qq.api.service.QQ;
import com.liwh.social.qq.connet.QQAdapter;
import com.liwh.social.qq.connet.QQServiceProvider;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author: Liwh
 * @ClassName: QQConnectionFactory
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-26 2:53 PM
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    /*
        1、又来了个providerId，通过配置文件配置
        2、发现没。这一套流程都是一层一层调用，所以我们都不要注入Spring。让整套都保持多态，使得数据都是一对一的
        3、构造器改成我们的，里面调super就是
           Factory的创建在这里不是new出来的，是Social Auto配置的，这个到时再说
    */
    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapter());
    }
}
