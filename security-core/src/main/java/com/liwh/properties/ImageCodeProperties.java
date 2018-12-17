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
public class ImageCodeProperties extends SmsCodeProperties {
    private int width = 67;
    private int high = 23;

    public ImageCodeProperties() {
        setLength(4);
    }
}
