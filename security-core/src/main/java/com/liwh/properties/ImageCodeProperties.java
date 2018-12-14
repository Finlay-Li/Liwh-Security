package com.liwh.properties;

import lombok.Data;

/**
 * @author: Liwh
 * @ClassName: ImageCodeProperties
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-14 4:47 PM
 */
@Data
public class ImageCodeProperties {
    private int width = 67;
    private int high = 23;
    private int length = 4;
    private int expireTime = 60;
    private String uri = "/user/*";
}
