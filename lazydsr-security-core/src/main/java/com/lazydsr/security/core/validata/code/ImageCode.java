package com.lazydsr.security.core.validata.code;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * ImageCode
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.security.core.validata.code
 * Created by Lazy on 2018/1/22 2:28
 * Version: 0.1
 * Info: 图片验证码
 */
@Data
public class ImageCode {
    private BufferedImage image;
    private String code;
    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, int expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }
}
