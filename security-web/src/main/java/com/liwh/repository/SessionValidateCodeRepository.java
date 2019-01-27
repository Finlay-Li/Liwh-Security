package com.liwh.repository;

import com.liwh.enums.ValidateCodeType;
import com.liwh.validate.model.ValidateCode;
import com.liwh.validate.repository.ValidateCodeRepository;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import static com.liwh.validate.processor.ValidateCodeProcessor.SESSION_VALIDATE_PREFIX;

/**
 * @author: Liwh
 * @ClassName: SessionRepository
 * @Description:
 * @version: 1.0.0
 * @date: 2019-01-28 3:23 AM
 */
@Component("sessionValidateCodeRepository")
public class SessionValidateCodeRepository implements ValidateCodeRepository {

    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void save(ServletWebRequest servletWebRequest, ValidateCode validateCode, ValidateCodeType validateCodeType) {
        sessionStrategy.setAttribute(servletWebRequest, getSessionKey(validateCodeType), validateCode);

    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return (ValidateCode) sessionStrategy.getAttribute(request, getSessionKey(validateCodeType));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {
        sessionStrategy.removeAttribute(request, getSessionKey(codeType));
    }

    /**
     * 构建验证码放入session时的key
     */
    private String getSessionKey(ValidateCodeType validateCodeType) {
        return SESSION_VALIDATE_PREFIX + validateCodeType.toString().toUpperCase();
    }
}
