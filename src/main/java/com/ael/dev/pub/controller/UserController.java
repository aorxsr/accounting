package com.ael.dev.pub.controller;

import com.ael.configuration.entity.Users;
import com.ael.configuration.repository.UserRepository;
import com.ael.configuration.response.ResponseDataUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    @ResponseBody
    @PostMapping(value = "/login")
    public Object login(Users users) {
        UsernamePasswordToken token = new UsernamePasswordToken(users.getUsername(), users.getPassword());
        try {
            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException e) {
            return ResponseDataUtil.buildError("请检查用户名!");
        } catch (IncorrectCredentialsException e) {
            return ResponseDataUtil.buildError("请检查密码!");
        }
        return ResponseDataUtil.buildSuccess();
    }

    /* 用户相关的crud */
    @ResponseBody
    @GetMapping(value = "/user/all")
    public Object getAllUser(@RequestBody Pageable pageable) {
        // TODO: 2020-07-31 这里请求参数要换成单个的,RequestBody只能接受POST的
        return userRepository.findAll(pageable);
    }

}
