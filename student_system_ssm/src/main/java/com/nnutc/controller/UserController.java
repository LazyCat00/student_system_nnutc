package com.nnutc.controller;


import com.nnutc.bean.User;
import com.nnutc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@CrossOrigin//跨域访问
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @GetMapping("/login")
    public void login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("user-login-start");
        User user = userService.login(username, password);

    }
}
