package com.lazydsr.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SecurityProperties
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.security.core.properties
 * Created by Lazy on 2018/1/21 21:36
 * Version: 0.1
 * Info: @TODO:...
 */
@Data
@ConfigurationProperties(prefix = "lazydsr.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();

}
