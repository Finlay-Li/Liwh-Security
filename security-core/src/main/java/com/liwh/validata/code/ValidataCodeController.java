package com.liwh.validata.code;

import com.liwh.properties.SecurityProperties;
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
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

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
    private final String SESSION_ID = "SESSION_KEY_IMAGE_CODE";

    @Autowired
    SecurityProperties securityProperties;

    /**
     * 图片，因此要写出到响应流中
     */
    @GetMapping("/image/code")
    public void imageCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //用随机数生成图片验证码
        ImageCode imageCode = CreateImageCode(request);
        //将图片验证码存储在session中
        //session: key value
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_ID, imageCode);
        //使用ImageIO把图片验证码的图片流（RenderedImage要渲染的图像）写到响应流中
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

    private ImageCode CreateImageCode(HttpServletRequest request) {
        //图片宽高
        //请求级配置ServletRequestUtils
        int width = ServletRequestUtils.getIntParameter(request,"width",securityProperties.getValidataCode().getImageCode().getWidth());
        int height = ServletRequestUtils.getIntParameter(request,"height",securityProperties.getValidataCode().getImageCode().getHigh());

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        //4 位数
        for (int i = 0; i < securityProperties.getValidataCode().getImageCode().getLength(); i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();

        //expireTime = 60
        return new ImageCode(image, sRand, securityProperties.getValidataCode().getImageCode().getExpireTime());
    }

    /**
     * 生成随机背景条纹
     *
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
