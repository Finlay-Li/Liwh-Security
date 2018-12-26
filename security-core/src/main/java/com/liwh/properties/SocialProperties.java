package com.liwh.properties;

import lombok.Data;

/**
 * @author: Liwh
 * @ClassName: SocialProperties
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-26 6:59 PM
 */
@Data
public class SocialProperties {

    private QQProperties qq = new QQProperties();
    private WXProperties wx = new WXProperties();

}
