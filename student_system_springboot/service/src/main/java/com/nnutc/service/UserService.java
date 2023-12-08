package com.nnutc.service;


import com.nnutc.common.vo.ResultVO;

public interface UserService {
    //用户登录
    public ResultVO login(String username, String password);

}
