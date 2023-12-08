package com.nnutc.controller;

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

    @GetMapping("/login")
    public ResultVO login(@RequestParam("username") String username,
                          @RequestParam(value = "password") String password) {
        System.out.println("user/login-----start");
        ResultVO resultVO = userService.login(username, password);
        return resultVO;
    }
}
