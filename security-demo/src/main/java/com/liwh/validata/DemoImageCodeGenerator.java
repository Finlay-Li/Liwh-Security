package com.liwh.validata;

import com.liwh.validata.code.ImageCode;
import com.liwh.validata.code.ImageCodeGenerator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Liwh
 * @ClassName: DemoImageCodeGenerator
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-16 10:57 AM
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ImageCodeGenerator {


    @Override
    public ImageCode generator(HttpServletRequest request) {
        System.out.println("外部扩展的图片验证码生成逻辑");
        return null;
    }
}
