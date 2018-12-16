package com.liwh.validata.code;

import com.liwh.properties.SecurityProperties;
import lombok.Data;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author: Liwh
 * @ClassName: DefultImageCodeGenerator
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-16 10:42 AM
 */
@Data
public class DefultImageCodeGenerator implements ImageCodeGenerator {

    private SecurityProperties securityProperties;

    @Override
    public ImageCode generator(HttpServletRequest request) {
        //图片宽高
        //请求级配置ServletRequestUtils
        int width = ServletRequestUtils.getIntParameter(request, "width", securityProperties.getValidataCode().getImageCode().getWidth());
        int height = ServletRequestUtils.getIntParameter(request, "height", securityProperties.getValidataCode().getImageCode().getHigh());

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
