package com.liwh.validate.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: Liwh
 * @ClassName: ValidateCode
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-17 7:23 PM
 */
@Data
public class ValidateCode implements Serializable {

    private static final long serialVersionUID = 5525871613324490446L;
    //验证码
    private String code;
    //过期时间
    private LocalDateTime expireTime;

    //常规构造方法
    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    //设置过期时间的构造方法
    public ValidateCode(String code, Integer expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    public boolean isExpireTime(LocalDateTime expireTime) {
        //当前时间已经大于验证码中的时间则过期了
        return LocalDateTime.now().isAfter(expireTime);
    }
}
