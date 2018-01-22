package com.lazydsr.security.core.properties;

import lombok.Data;

/**
 * ImageCodeProperties
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.security.core.properties
 * Created by Lazy on 2018/1/22 20:32
 * Version: 0.1
 * Info: @TODO:...
 */
@Data
public class ImageCodeProperties {
    private int width = 67;
    private int height = 23;
    private int length = 4;
    private int expireIn = 60;
    private String urls;

}
