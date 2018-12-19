package com.liwh.validate.processor;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: Liwh
 * @interfaceName: ValidataCodeProcessor
 * @Description:   把相同的处理步骤抽取出来
 * @version: 1.0.0
 * @date: 2018-12-17 7:02 PM
 */
public interface ValidateCodeProcessor {

    //session key前缀
    String SESSION_VALIDATE_PREFIX = "SESSION_VALIDATE_KEY_FOR_CODE_";

    //生成
    //ServletWebRequest实际包含了request,response
    void create(ServletWebRequest servletWebRequest)throws Exception;

    //校验验证码
    void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException;
}
