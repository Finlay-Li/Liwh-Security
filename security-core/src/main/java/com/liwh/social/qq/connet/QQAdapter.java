package com.liwh.social.qq.connet;

import com.liwh.social.qq.api.model.QQUserInfo;
import com.liwh.social.qq.api.service.QQ;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author: Liwh
 * @ClassName: QQAdapter
 * @Description: 适配ConnectionValues
 * @version: 1.0.0
 * @date: 2018-12-26 2:11 PM
 */
public class QQAdapter implements ApiAdapter<QQ> {

    //判断QQApi服务是否可用
    @Override
    public boolean test(QQ qq) {
        return true;
    }

    //适配Connection和API所拿到的用户信息
    //泛型原因：所以要指定API的类型
    //OpenId我们在QQApiImpl中记得设置进去
    @Override
    public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {
        QQUserInfo userInfo = qq.getQQUserInfo();
        connectionValues.setDisplayName(userInfo.getNickname());
        connectionValues.setImageUrl(userInfo.getFigureUrl_qq_1());
        connectionValues.setProfileUrl(null);
        connectionValues.setProviderUserId(userInfo.getOpenId());
    }

    //解绑操作
    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    //个人主页状态
    @Override
    public void updateStatus(QQ qq, String s) {
        //do noting
    }
}
