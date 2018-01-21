package com.lazydsr.security.core.validata;

import com.lazydsr.security.core.validata.code.ImageCode;
import com.lazydsr.security.core.validata.controller.ValidataCodeController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * ValidataCodeFilter
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.security.core.validata
 * Created by Lazy on 2018/1/22 3:04
 * Version: 0.1
 * Info: @TODO:...
 */
@Slf4j
public class ValidataCodeFilter extends OncePerRequestFilter {
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private AuthenticationFailureHandler lazydsrAuthenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("进入验证码filter");
        if (StringUtils.equals("/lazydsr/authentication/require", request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            try {
                validata(new ServletWebRequest(request));
            } catch (ValidataCodeException e) {
                lazydsrAuthenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validata(ServletWebRequest request) throws ServletRequestBindingException {
        ImageCode imageCode = (ImageCode) sessionStrategy.getAttribute(request, ValidataCodeController.SESSION_KEY);
        String code = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        if (StringUtils.isBlank(code)) {
            throw new ValidataCodeException("验证码不能为空");
        }
        if (imageCode == null) {
            throw new ValidataCodeException("验证码不存在");

        }
        if (imageCode.getExpireTime().isAfter(LocalDateTime.now())) {
            sessionStrategy.removeAttribute(request, ValidataCodeController.SESSION_KEY);
            throw new ValidataCodeException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(code, imageCode.getCode())) {
            throw new ValidataCodeException("验证码不匹配");

        }
        sessionStrategy.removeAttribute(request, ValidataCodeController.SESSION_KEY);
    }

    public void setLazydsrAuthenticationFailureHandler(AuthenticationFailureHandler lazydsrAuthenticationFailureHandler) {
        this.lazydsrAuthenticationFailureHandler = lazydsrAuthenticationFailureHandler;
    }
}