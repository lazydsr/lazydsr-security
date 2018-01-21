package com.lazydsr.security.controller;

import com.lazydsr.security.dto.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * UserController
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.securitydemo.controller
 * Created by Lazy on 2018/1/20 18:23
 * Version: 0.1
 * Info: @TODO:...
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping
    public User add(@Valid User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
            return null;
        }

        user.setId("1");
        return user;
    }

    @GetMapping
    public List<User> find(String username) {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        System.out.printf("id:" + id);
        User user = new User();
        user.setUsername("11111111111");
        return user;
    }
}
