package com.lazydsr.security.browser.support;

import lombok.Data;

/**
 * SimpleResponse
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.security.browser.support
 * Created by Lazy on 2018/1/21 21:26
 * Version: 0.1
 * Info: @TODO:...
 */
@Data
public class SimpleResponse {
    Object content;

    public SimpleResponse() {
    }

    public SimpleResponse(Object content) {
        this.content = content;
    }
}
