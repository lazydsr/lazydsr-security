package com.lazydsr.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lazydsr.security.browser.support.SimpleResponse;
import com.lazydsr.security.core.properties.LoginType;
import com.lazydsr.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * LazydsrAuthenticationFailureHandler
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.security.browser.authentication
 * Created by Lazy on 2018/1/21 22:24
 * Version: 0.1
 * Info: @TODO:...
 */
@Component
@Slf4j
public class LazydsrAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("用户登录失败");
        if (LoginType.VIEW.equals(securityProperties.getBrowser().getLoginType())) {
            super.onAuthenticationFailure(request, response, exception);
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
        }
    }
}
