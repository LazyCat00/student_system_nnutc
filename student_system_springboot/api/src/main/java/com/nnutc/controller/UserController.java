package com.nnutc.controller;

import com.nnutc.bean.User;
import com.nnutc.common.vo.ResultVO;
import com.nnutc.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
//@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用；
@RequestMapping("/user")
@CrossOrigin//跨域访问
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ResultVO login(@RequestParam("username") String username,
                          @RequestParam(value = "password") String password) {
        System.out.println("user/login-----start");
        return userService.login(username, password);
    }

    @PostMapping("register")
    public ResultVO register(@RequestBody User user) {
        System.out.println("user/register-----start");
        return userService.register(user);
    }
}
