package com.lazydsr.security.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * User
 * PROJECT_NAME: lazydsr-security
 * PACKAGE_NAME: com.lazydsr.securitydemo.dto
 * Created by Lazy on 2018/1/20 18:23
 * Version: 0.1
 * Info: @TODO:...
 */
@Data
public class User {
    String id;
    String username;
    @NotBlank(message = "用户密码不能为空")
    String password;
}
