package com.lazydsr.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lazydsr.security.core.properties.LoginType;
import com.lazydsr.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * LazydsrAuthenticationSuccessHandler
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.security.browser.authentication
 * Created by Lazy on 2018/1/21 22:07
 * Version: 0.1
 * Info: @TODO:...
 */
@Component
@Slf4j
public class LazydsrAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("用户登录成功");
        if (LoginType.VIEW.equals(securityProperties.getBrowser().getLoginType())) {
            super.onAuthenticationSuccess(request, response, authentication);
        } else {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }
    }
}
