package com.liwh.validate.adapter;


import com.liwh.enums.ValidateCodeType;
import com.liwh.validate.exception.ValidateCodeException;
import com.liwh.validate.processor.ValidateCodeProcessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Liwh
 * @interfaceName: ValidateCodeProcessorManager
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-19 11:16 AM
 */
@Component
public class ValidateCodeProcessorAdapter {

    @Autowired
    private Map<String, ValidateCodeProcessor> codeProcessorMap = new HashMap<>();

    //通过类型，自动提供支持的Processor
    public ValidateCodeProcessor support(ValidateCodeType type) {
        String var1 = type.toString().toLowerCase();
        String var2 = var1 + StringUtils.substringBefore(ValidateCodeProcessor.class.getSimpleName(), "CodeProcessor");
        ValidateCodeProcessor processor = codeProcessorMap.get(var2);
        if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + var2 + "不存在");
        }
        return processor;
    }
}
