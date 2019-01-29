package com.liwh.validate.filter;

import com.liwh.constants.SecurityConstants;
import com.liwh.enums.ValidateCodeType;
import com.liwh.properties.SecurityProperties;
import com.liwh.validate.adapter.ValidateCodeProcessorAdapter;
import com.liwh.validate.exception.ValidateException;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: Liwh
 * @ClassName: ValidataCodeFilter
 * @Description: 校验过滤器
 * @version: 1.0.0
 * @date: 2018-12-12 10:52 AM
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    //存储uri
    //private Set<String> uris = new HashSet();
    private Map<String, ValidateCodeType> validateUrls = new HashMap<>();
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationFailureHandler defaultAuthenticationFailureHandle;
    @Autowired
    private ValidateCodeProcessorAdapter validateCodeProcessorAdapter;

    //存储要验证的urls
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        //默认的验证登录请求
//        validateUrls.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        validateUrls.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        //properties中配置的请求urls
        putPropertiesValidateUrls(validateUrls);
    }

    private void putPropertiesValidateUrls(Map<String, ValidateCodeType> validateUrls) {
        String var1 = securityProperties.getValidateCode().getImageCode().getUri();
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(var1, ",");
        for (String configUrl :
                configUrls) {
            validateUrls.put(configUrl, ValidateCodeType.IMAGE);
        }

        String var2 = securityProperties.getValidateCode().getSmsCode().getUri();
        StringUtils.splitByWholeSeparatorPreserveAllTokens(var2, ",");
        for (String configUrl :
                configUrls) {
            validateUrls.put(configUrl, ValidateCodeType.SMS);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//      boolean action = false;
        //获取Type
        ValidateCodeType type = getValidateCodeType(request);

        //进入校验
        if (type != null) {

            try {
                //获取验证的类型：要验证的请求都是不定的，并且不可能一定以TYPE 的值开头
                //但是，这些请求都是可以配置的！那么我们就可以用MAP以TYPE 的值做Key。存储起来
                //如果进来的请求与某个类型的value匹配，那么就是该类型

                //但是，如果按正常思路走，TYPE是Key，Value是Url，那在pathMatcher.match（）时就会很麻烦
                //因此，逆向思维：Url是Key，Value是TYPE
                //那怎么样才进入校验呢？当这个type获取到时，证明是配置要校验的路径，进入校验；
                //注意：这里的拦截，每个请求都会进入的！
                logger.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型：" + type);

                //进行校验
                validateCodeProcessorAdapter.support(type).validate(new ServletWebRequest(request, response));
                logger.info("验证码校验通过");

            } catch (ValidateException e) {
                //校验失败时：会抛出自定义异常然后就被catch到了
                defaultAuthenticationFailureHandle.onAuthenticationFailure(request, response, e);

                //不执行doFilter了
                return;
            }
        }


        //不是要校验的请求，放过到下一个filter，spring会选择provider filter处理
        filterChain.doFilter(request, response);
    }

    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            if (MapUtils.isNotEmpty(validateUrls)) {
                Set<String> keys = validateUrls.keySet();
                for (String url : keys) {
                    boolean match = antPathMatcher.match(url, request.getRequestURI());
                    if (match) {
                        ValidateCodeType result = validateUrls.get(url);
                        return result;
                    }
                }
            }
        }
        return null;
    }
}
