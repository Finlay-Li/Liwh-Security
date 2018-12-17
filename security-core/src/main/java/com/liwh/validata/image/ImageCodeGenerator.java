package com.liwh.validata.image;


import javax.servlet.http.HttpServletRequest;

/**
 * @author: Liwh
 * @ClassName: ImageCodeGenerator
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-16 10:40 AM
 */
public interface ImageCodeGenerator {

    ImageCode generator(HttpServletRequest request);
}
