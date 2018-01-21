package com.lazydsr.security.core.validata;

import org.springframework.security.core.AuthenticationException;

/**
 * ValidataCodeException
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.security.core.validata
 * Created by Lazy on 2018/1/22 3:28
 * Version: 0.1
 * Info: @TODO:...
 */
public class ValidataCodeException extends AuthenticationException {
    public ValidataCodeException(String msg) {
        super(msg);
    }
}
