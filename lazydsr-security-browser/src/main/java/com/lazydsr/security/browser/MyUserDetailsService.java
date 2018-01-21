package com.lazydsr.security.browser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * MyUserDetailsService
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.security.browser
 * Created by Lazy on 2018/1/21 18:26
 * Version: 0.1
 * Info: @TODO:...
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("用户登录名：" + username);
        log.info("pwd:" + passwordEncoder.encode("1"));
        String pwd = passwordEncoder.encode("1");
        log.info("pwd2:" + passwordEncoder.encode("1"));

        return new User(username, pwd, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

    }
}
