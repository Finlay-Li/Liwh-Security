package com.liwh.abstracts;


import com.liwh.properties.SecurityProperties;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @ClassName: AbstractUserDetails
 * @Description: 获取当前用户抽象类
 */
public abstract class AbstractUserDetails<T> {
    private static final String HEADER = "Authorization";
    private static final String HEADER_FIX = "bearer ";
    private SecurityProperties securityProperties;

    public AbstractUserDetails(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public T getUser(HttpServletRequest request) {
        String header = request.getHeader(HEADER);
        if (!StringUtils.startsWith(header, HEADER_FIX)) {
            return null;
        }

        String token = StringUtils.substringAfter(header, HEADER_FIX);

        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
                    .parseClaimsJws(token).getBody();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (SignatureException | MalformedJwtException e) {
            // don't trust the JWT!
            // jwt 解析错误
        } catch (ExpiredJwtException e) {
            // jwt 已经过期，在设置jwt的时候如果设置了过期时间，过期则会抛出这个异常，我们可以抓住这个异常并作相关处理。
        }

        if (claims == null) {
            return null;
        }

        String userName = (String) claims.get("user_name");

        return parserUser(userName);
    }

    protected abstract T parserUser(String userName);
}
