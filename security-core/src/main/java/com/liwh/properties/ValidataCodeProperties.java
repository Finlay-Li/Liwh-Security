package com.liwh.properties;


import lombok.Data;

/**
 * @author: Liwh
 * @ClassName: ValidataCodeProperties
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-14 2:50 PM
 */
@Data
public class ValidataCodeProperties {
    //图片验证码
    private ImageCodeProperties imageCode = new ImageCodeProperties();

    //短信验证码
}
