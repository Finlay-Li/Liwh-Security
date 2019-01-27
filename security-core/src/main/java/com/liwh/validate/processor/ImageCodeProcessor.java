package com.liwh.validate.processor;

import com.liwh.constants.SecurityConstants;
import com.liwh.validate.model.ImageCode;
import com.liwh.validate.model.ValidateCode;
import com.liwh.validate.repository.ValidateCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
@Component
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Override
    void send(ServletWebRequest servletWebRequest, ImageCode validateCode) throws Exception {
        ImageIO.write(validateCode.getImage(), SecurityConstants.DEFAULT_WRITE_IMAGE_TYPE, servletWebRequest.getResponse().getOutputStream());
    }
}
