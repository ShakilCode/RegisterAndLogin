package com.example.sign.up.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminContentController {
     
    @GetMapping("/adminlogin")
    public String adminlogin(){
        return "/adminregisterandlogin/adminlogin";
    }

    @GetMapping("/req/adminsignup")
    public String adminsignup(){
        return "/adminregisterandlogin/adminsignup";
    }

    @GetMapping("/index")
    public String home(){
        return "/adminregisterandlogin/index";
    }
}
