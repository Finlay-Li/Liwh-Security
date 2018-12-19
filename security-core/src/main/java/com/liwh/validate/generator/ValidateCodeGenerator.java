package com.liwh.validate.generator;

import com.liwh.validate.model.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: Liwh
 * @interfaceName: ValidateCodeGenerator
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-17 7:56 PM
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest servletWebRequest);
}
