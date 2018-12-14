package com.liwh.validata.code;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author: Liwh
 * @ClassName: ImageCode
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-11 5:59 PM
 */
@Data
public class ImageCode {

    //图片
    private BufferedImage image;
    //验证码
    private String code;
    //过期时间
    private LocalDateTime expireTime;

    //常规构造方法
    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    //设置过期时间的构造方法
    public ImageCode(BufferedImage image, String code, Integer expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    public boolean isExpireTime(LocalDateTime expireTime){
        //当前时间已经大于验证码中的时间则过期了
        return LocalDateTime.now().isAfter(expireTime);
    }
}
