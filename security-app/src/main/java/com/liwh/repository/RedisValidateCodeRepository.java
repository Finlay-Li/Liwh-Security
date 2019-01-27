package com.liwh.repository;

import com.liwh.enums.ValidateCodeType;
import com.liwh.validate.model.ValidateCode;
import com.liwh.validate.repository.ValidateCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: Liwh
 * @ClassName: RedisValidateCodeRepository
 * @Description: 基于redis的验证码存取器
 * @version: 1.0.0
 * @date: 2019-01-28 4:36 AM
 */
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void save(ServletWebRequest servletWebRequest, ValidateCode validateCode, ValidateCodeType validateCodeType) {

    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return null;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {

    }
}
