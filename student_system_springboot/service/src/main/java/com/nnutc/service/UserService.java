package com.nnutc.service;


import com.nnutc.bean.User;
import com.nnutc.common.vo.ResultVO;

public interface UserService {
    //用户登录
    public ResultVO login(String username, String password);
    public ResultVO register(User user);

}
