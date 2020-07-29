package com.ael.dev.pub.controller;

import com.ael.configuration.entity.Users;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping(value = "/login")
    public Object login(Users users) {
        UsernamePasswordToken token = new UsernamePasswordToken(users.getUsername(), users.getPassword());
        try {
            SecurityUtils.getSubject().login(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/";
    }

}
