package com.lazydsr.security.core;

import com.lazydsr.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * SecurityCoreConfig
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.security.core.properties
 * Created by Lazy on 2018/1/21 21:45
 * Version: 0.1
 * Info: @TODO:...
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
