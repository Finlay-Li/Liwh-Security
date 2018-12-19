package com.liwh.validate.processor;

import com.liwh.constants.SecurityConstants;
import com.liwh.validate.model.ImageCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @author: Liwh
 * @ClassName: ImageCodeProcessor
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-17 8:23 PM
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {


    @Override
    void save(ServletWebRequest servletWebRequest, ImageCode validateCode) throws Exception {
        sessionStrategy.setAttribute(servletWebRequest, SESSION_VALIDATE_PREFIX + "IMAGE", validateCode);
    }

    @Override
    void send(ServletWebRequest servletWebRequest, ImageCode validateCode) throws Exception {
        ImageIO.write(validateCode.getImage(), SecurityConstants.DEFAULT_WRITE_IMAGE_TYPE, servletWebRequest.getResponse().getOutputStream());
    }
}
