package com.liwh.validata;

import com.liwh.validate.code.ValidateCodeGenerator;
import com.liwh.validate.image.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: Liwh
 * @ClassName: DemoImageCodeGenerator
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-16 10:57 AM
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {


    @Override
    public ImageCode generate(ServletWebRequest servletWebRequest) {
        System.out.println("外部扩展的图片验证码生成逻辑");
        return null;
    }
}
