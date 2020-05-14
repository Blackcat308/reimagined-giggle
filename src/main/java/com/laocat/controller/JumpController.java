package com.laocat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Lao Cat
 * @Date: 2020/5/13 16:30
 */
@Controller
@RequestMapping
public class JumpController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }


    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
