package com.lazydsr.security.browser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * MyPasswordEncoder
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.security.browser
 * Created by Lazy on 2018/1/21 18:52
 * Version: 0.1
 * Info: @TODO:...
 */
@Slf4j
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        log.error("encode:" + rawPassword.toString());
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        log.error("matches1:" + rawPassword.toString());
        log.error("matches2:" + encodedPassword);
        if (rawPassword != null && !rawPassword.toString().equals("")) {
            if (rawPassword.toString().equals(encodedPassword))
                return true;
        }
        return false;
    }
}
