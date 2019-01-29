package com.liwh.properties;

import lombok.Data;

/**
 * @author: Liwh
 * @ClassName: OAuth2Properties
 * @Description:
 * @version: 1.0.0
 * @date: 2019-01-29 10:59 PM
 */
@Data
public class OAuth2Properties {
    private OAuth2ClientProperties[] clients = {};
}
