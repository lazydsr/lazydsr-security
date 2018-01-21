package com.lazydsr.security.core.properties;

import lombok.Data;

/**
 * BrowserProperties
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.security.core.properties
 * Created by Lazy on 2018/1/21 21:37
 * Version: 0.1
 * Info: @TODO:...
 */
@Data
public class BrowserProperties {
    private String loginPage = "/lazydsr-login.html";
    private Enum loginType=LoginType.VIEW;
}
