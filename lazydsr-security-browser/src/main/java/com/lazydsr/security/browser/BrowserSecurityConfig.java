package com.lazydsr.security.browser;

import com.lazydsr.security.browser.authentication.LazydsrAuthenticationSuccessHandler;
import com.lazydsr.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

/**
 * BrowserSecurityConfig
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.security.browser
 * Created by Lazy on 2018/1/21 17:24
 * Version: 0.1
 * Info: @TODO:...
 */
@Configuration
@Slf4j
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private LazydsrAuthenticationSuccessHandler successHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //return new MyPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/authentication/require").failureUrl("/authentication/require?error")
                .loginProcessingUrl("/authentication/form").successHandler(successHandler)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require", securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated();

        http.csrf().disable();
    }
}
