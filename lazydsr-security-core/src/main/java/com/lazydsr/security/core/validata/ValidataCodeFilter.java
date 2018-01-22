package com.lazydsr.security.core.validata;

import com.lazydsr.security.core.properties.SecurityProperties;
import com.lazydsr.security.core.validata.code.ImageCode;
import com.lazydsr.security.core.validata.controller.ValidataCodeController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
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
import java.util.HashSet;
import java.util.Set;

/**
 * ValidataCodeFilter
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.security.core.validata
 * Created by Lazy on 2018/1/22 3:04
 * Version: 0.1
 * Info: @TODO:...
 */
@Slf4j
public class ValidataCodeFilter extends OncePerRequestFilter implements InitializingBean {
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private AuthenticationFailureHandler lazydsrAuthenticationFailureHandler;

    private SecurityProperties securityProperties;
    private Set<String> set = new HashSet<>();
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getValidata().getImage().getUrls(), ",");
        for (String url : urls) {
            set.add(url);
        }
        set.add("/lazydsr/authentication/form");

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Boolean next = false;
        for (String url : set) {
            if (antPathMatcher.match(url, request.getRequestURI())) {
                next = true;
                break;
            }
        }

        //if (next && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
        if (next ) {
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
        if (imageCode.getExpireTime().isBefore(LocalDateTime.now())) {
            sessionStrategy.removeAttribute(request, ValidataCodeController.SESSION_KEY);
            throw new ValidataCodeException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(code, imageCode.getCode())) {
            throw new ValidataCodeException("验证码不匹配");

        }
        sessionStrategy.removeAttribute(request, ValidataCodeController.SESSION_KEY);
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public void setLazydsrAuthenticationFailureHandler(AuthenticationFailureHandler lazydsrAuthenticationFailureHandler) {

        this.lazydsrAuthenticationFailureHandler = lazydsrAuthenticationFailureHandler;
    }
}