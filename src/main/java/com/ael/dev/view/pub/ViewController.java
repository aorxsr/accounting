package com.ael.dev.view.pub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "/public/index";
    }

    @RequestMapping(value = {"/login"})
    public String login() {
        return "/public/login";
    }

    @RequestMapping(value = {"/login1"})
    public String login1() {
        return "/public/login-1";
    }

    @RequestMapping(value = {"/login2"})
    public String login2() {
        return "/public/login-2";
    }
}
