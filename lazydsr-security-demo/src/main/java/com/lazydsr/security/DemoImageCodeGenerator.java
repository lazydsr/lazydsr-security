package com.lazydsr.security;

import com.lazydsr.security.core.validata.ValidataCodeGenerator;
import com.lazydsr.security.core.validata.code.ImageCode;
import com.sun.javafx.iio.ImageStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import java.awt.image.BufferedImage;

/**
 * DemoImageCodeGenerator
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.security
 * Created by Lazy on 2018/1/22 22:23
 * Version: 0.1
 * Info: @TODO:...
 */
@Component(value = "imageCodeGenerator")
@Slf4j
public class DemoImageCodeGenerator implements ValidataCodeGenerator {
    @Override
    public ImageCode createImageValidataCode(ServletRequest request) {
        log.error("自定义的图片验证码生成器");
        return new ImageCode(new BufferedImage(100,40, 200),"1000",60);
    }
}
