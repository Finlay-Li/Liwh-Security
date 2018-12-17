package com.liwh.validata.sms;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Liwh
 * @ClassName: SmsCode
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-17 4:04 PM
 */
@Data
public class SmsCode {
    //验证码
    private String code;
    //过期时间
    private LocalDateTime expireTime;

    //常规构造方法
    public SmsCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    //设置过期时间的构造方法
    public SmsCode( String code, Integer expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    public boolean isExpireTime(LocalDateTime expireTime){
        //当前时间已经大于验证码中的时间则过期了
        return LocalDateTime.now().isAfter(expireTime);
    }
}
