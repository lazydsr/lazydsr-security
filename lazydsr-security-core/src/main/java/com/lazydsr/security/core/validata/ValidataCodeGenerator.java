package com.lazydsr.security.core.validata;

import com.lazydsr.security.core.validata.code.ImageCode;

import javax.servlet.ServletRequest;

/**
 * ValidataCodeGenerator
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.security.core.validata
 * Created by Lazy on 2018/1/22 22:07
 * Version: 0.1
 * Info: @TODO:...
 */
public interface ValidataCodeGenerator {
    ImageCode createImageValidataCode(ServletRequest request);
}
