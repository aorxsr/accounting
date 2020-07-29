package com.ael.dev.pub.layuimini.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/pub/layuimini")
@RestController
public class LayuiminiController {

    @GetMapping(value = "/init.json")
    public Object initJson() {
        return "asdf";
    }

}
