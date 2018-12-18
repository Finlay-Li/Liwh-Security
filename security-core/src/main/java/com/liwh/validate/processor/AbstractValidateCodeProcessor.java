package com.liwh.validate.processor;

import com.liwh.validate.code.ValidateCode;
import com.liwh.validate.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * @author: Liwh
 * @ClassName: AbstractValidateCodeProcessor
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-17 7:17 PM
 */
public abstract class AbstractValidateCodeProcessor<T extends ValidateCode> implements ValidateCodeProcessor {

    //依赖搜索
    @Autowired
    Map<String, ValidateCodeGenerator> codeGenerators = new HashMap();

    @Override
    public void create(ServletWebRequest servletWebRequest) throws Exception {
        //生成
        T validateCode = generate(servletWebRequest);
        //存储
        save(servletWebRequest, validateCode);
        //发送
        send(servletWebRequest, validateCode);
    }

    private T generate(ServletWebRequest servletWebRequest) {
        //生成有不同的生成方式。那么就有不同的generator实现
        //这是第二层的接口
        //好，接口有多个实现，都注册在spring中，我们就可以集中起来，按需使用实现类！
        //这叫：依赖搜索
        String uri = servletWebRequest.getRequest().getRequestURI();
        String[] split = uri.split("/");
        String type = split[split.length - 1].toLowerCase();
        String keyName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator generator = codeGenerators.get(keyName);
        if (generator == null){
            throw new RuntimeException("验证码生成器" + keyName + "不存在");
        }
        //自动选择生成器
        T validateCode = (T) generator.generate(servletWebRequest);

        return validateCode;
    }

    //由子类实现各自的逻辑
    abstract void save(ServletWebRequest servletWebRequest, T validateCode) throws Exception;

    //由子类实现各自的逻辑
    abstract void send(ServletWebRequest servletWebRequest, T validateCode) throws Exception;

}
