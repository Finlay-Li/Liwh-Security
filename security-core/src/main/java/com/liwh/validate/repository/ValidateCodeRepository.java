package com.liwh.validate.repository;

import com.liwh.enums.ValidateCodeType;
import com.liwh.validate.model.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: Liwh
 * @InterfaceName: ValidateCodeRepository
 * @Description:
 * @version: 1.0.0
 * @date: 2019-01-28 2:56 AM
 */
public interface ValidateCodeRepository {

    /**
     * 保存验证码
     */
    void save(ServletWebRequest servletWebRequest, ValidateCode validateCode,ValidateCodeType validateCodeType);

    /**
     * 获取验证码
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);

    /**
     * 移除验证码
     */
    void remove(ServletWebRequest request, ValidateCodeType codeType);
}
