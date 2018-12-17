package com.liwh.properties;

import lombok.Data;

/**
 * @author: Liwh
 * @ClassName: SmsCodeProperties
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-17 4:54 PM
 */
@Data
public class SmsCodeProperties {

    private int length = 6;
    private int expireTime = 60;
    private String uri = "/user/*";
}
