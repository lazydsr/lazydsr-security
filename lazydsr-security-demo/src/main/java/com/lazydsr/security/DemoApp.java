package com.lazydsr.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoApp
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.securitydemo
 * Created by Lazy on 2018/1/20 16:08
 * Version: 0.1
 * Info: @TODO:...
 */
@SpringBootApplication
@RestController
public class DemoApp {
    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello word";
    }
}
