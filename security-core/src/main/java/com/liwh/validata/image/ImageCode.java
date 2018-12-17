package com.liwh.validata.image;

import com.liwh.validata.sms.SmsCode;
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
public class ImageCode extends SmsCode {

    //图片
    private BufferedImage image;

    //常规构造方法
    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    //设置过期时间的构造方法
    public ImageCode(BufferedImage image, String code, Integer expireTime) {
        super(code, expireTime);
        this.image = image;
    }

}
