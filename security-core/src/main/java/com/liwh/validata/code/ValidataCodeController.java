package com.liwh.validata.code;

import com.liwh.validata.image.ImageCode;
import com.liwh.validata.image.ImageCodeGenerator;
import com.liwh.validata.sms.SmsCode;
import com.liwh.validata.sms.SmsCodeGenerator;
import com.liwh.validata.sms.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Liwh
 * @ClassName: ValidataCodeController
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-11 6:22 PM
 */
@RestController
public class ValidataCodeController {

    //social 提供的，用于操作session
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private final String SESSION_IMAGE_ID = "SESSION_KEY_IMAGE_CODE";
    private final String SESSION_SMS_ID = "SESSION_KEY_SMS_CODE";
    private final String IMAGE_TYPE = "JPEG";
    private final String MOBILE_NAME = "mobile";
    @Autowired
    private ImageCodeGenerator imageCodeGenerator;

    @Autowired
    private SmsCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsSender smsSender;

    /**
     * 图片，因此要写出到响应流中
     */
    @GetMapping("/code/image")
    public void imageCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //用随机数生成图片验证码
        ImageCode imageCode = imageCodeGenerator.generator(request);
        //将图片验证码存储在session中
        //session: key value
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_IMAGE_ID, imageCode);
        //使用ImageIO把图片验证码的图片流（RenderedImage要渲染的图像）写到响应流中
        ImageIO.write(imageCode.getImage(), IMAGE_TYPE, response.getOutputStream());
    }

    /**
     * 短信验证码
     */
    @GetMapping("/code/sms")
    public void smsCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //用随机数生成短信验证码
        SmsCode smsCode = smsCodeGenerator.generator();
        //将图片验证码存储在session中
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_SMS_ID, smsCode);
        //获取用户输入的手机号
        String  mobile = ServletRequestUtils.getStringParameter(request, MOBILE_NAME);
        //发送短信
        smsSender.send(mobile, smsCode.getCode());
    }
}
