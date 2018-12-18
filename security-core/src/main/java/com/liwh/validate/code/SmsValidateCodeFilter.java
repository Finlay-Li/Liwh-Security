package com.liwh.validate.code;

import com.liwh.properties.SecurityProperties;
import com.liwh.validate.exception.ValidateException;
import com.liwh.validate.image.ImageCode;
import com.liwh.validate.processor.ValidateCodeProcessor;
import com.liwh.validate.sms.SmsCode;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author: Liwh
 * @ClassName: ValidataCodeFilter
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-12 10:52 AM
 */
@Data
public class SmsValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    //    @Autowired
    SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private AuthenticationFailureHandler defaultAuthenticationFailureHandle;
    //存储uri
    private Set<String> uris = new HashSet();
//    @Autowired
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
//    @Autowired
    private SecurityProperties securityProperties ;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String var1 = securityProperties.getValidateCode().getSmsCode().getUri();
        String[] configUris = StringUtils.splitByWholeSeparatorPreserveAllTokens(var1, ",");
        for (String configUri:
                configUris) {
            uris.add(configUri);
        }
        uris.add(securityProperties.getWebSecurity().getSmsLoginUri());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //是post请求
        if (StringUtils.equals(securityProperties.getWebSecurity().getSmsLoginUri(), request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {

            boolean action = false;

            for (String uri :
                    uris) {
                if (antPathMatcher.match(uri, request.getRequestURI())) {
                    action = true;
                }

                if (action) {
                    try {
                        //进行校验
                        validata(new ServletWebRequest(request));
                    } catch (ValidateException e) {
                        //校验失败：validata（）我们抛出自定义异常
                        //访问失败处理器进行处理
                        defaultAuthenticationFailureHandle.onAuthenticationFailure(request, response, e);

                        //不执行doFilter了
                        return;
                    }
                }
            }


        }

        //不是post请求，放过到下一个filter，spring会选择provider filter处理
        filterChain.doFilter(request, response);
    }

    private void validata(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        //从session中取出存储的验证码
        SmsCode sessionImageCode = (SmsCode) sessionStrategy.getAttribute(servletWebRequest, ValidateCodeProcessor.SESSION_VALIDATA_PREFIX+"SMS");

        //从请求中拿出用户输入（input框）的code
        String inputCode = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "smsCode");
        //各种校验
        if (StringUtils.isEmpty(inputCode)) {
            throw new ValidateException("验证码不能为空");
        }

        if (Objects.isNull(sessionImageCode)) {
            throw new ValidateException("验证码不存在");
        }
        if (sessionImageCode.isExpireTime(sessionImageCode.getExpireTime())) {
            sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeProcessor.SESSION_VALIDATA_PREFIX+"SMS");
            throw new ValidateException("验证码已过期");
        }

        if (!StringUtils.equalsIgnoreCase(sessionImageCode.getCode(), inputCode)) {
            throw new ValidateException("验证码不匹配");
        }

        //通过则从session中移除给验证码
        sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeProcessor.SESSION_VALIDATA_PREFIX+"SMS");
    }
}
