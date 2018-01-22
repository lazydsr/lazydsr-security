package com.lazydsr.security.core.validata;

import com.lazydsr.security.core.properties.SecurityProperties;
import com.lazydsr.security.core.validata.code.ImageCode;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.ServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * ImageCodeGeneratorImpl
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.security.core.validata
 * Created by Lazy on 2018/1/22 22:08
 * Version: 0.1
 * Info: @TODO:...
 */
@Data
public class ImageCodeGenerator implements ValidataCodeGenerator {

    private SecurityProperties securityProperties;

    @Override
    public ImageCode createImageValidataCode(ServletRequest request) {
        int width = ServletRequestUtils.getIntParameter(request, "width", securityProperties.getValidata().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request, "heigth", securityProperties.getValidata().getImage().getHeight());
        int length = securityProperties.getValidata().getImage().getLength();
        int expireIn = securityProperties.getValidata().getImage().getExpireIn();
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
        for (int i = 0; i < length; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();
        return new ImageCode(image, sRand, expireIn);

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
