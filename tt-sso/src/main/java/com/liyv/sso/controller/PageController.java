package com.liyv.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    /**
     * 登录页面
     * @return
     */
    @RequestMapping("page/login")
    public String showLogin(){
        return "login";
    }

    /**
     * 注册页面
     */
    @GetMapping("page/register")
    public String showRegister(){

        return "register";
    }
}
