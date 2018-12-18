package com.liwh.validate.processor;

import com.liwh.validate.image.ImageCode;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
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

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private final String IMAGE_TYPE = "JPEG";

    @Override
    void save(ServletWebRequest servletWebRequest, ImageCode validateCode) throws Exception {
        sessionStrategy.setAttribute(servletWebRequest, SESSION_VALIDATA_PREFIX + "IMAGE", validateCode);
    }

    @Override
    void send(ServletWebRequest servletWebRequest, ImageCode validateCode) throws Exception {
        ImageIO.write(validateCode.getImage(), IMAGE_TYPE, servletWebRequest.getResponse().getOutputStream());
    }
}
