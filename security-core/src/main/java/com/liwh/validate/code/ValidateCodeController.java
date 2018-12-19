package com.liwh.validate.code;

import com.liwh.validate.processor.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * @author: Liwh
 * @ClassName: ValidataCodeController
 * @Description: 验证服务
 * @version: 1.0.0
 * @date: 2018-12-11 6:22 PM
 */
@RestController
public class ValidateCodeController {

    @Autowired
    Map<String, ValidateCodeProcessor> codeProcessors = new HashMap<>();

    /**
     * 根据不同类型，调用{@link ValidateCodeProcessor}不同的实现
     */
    @GetMapping("/code/{type}")
    public void imageCode(HttpServletRequest request, HttpServletResponse response, @PathVariable("type") String type) throws Exception {
        //调用处理器完成验证码的功能
        codeProcessors.get(type + "ValidateCodeProcessor").create(new ServletWebRequest(request, response));
    }
}
