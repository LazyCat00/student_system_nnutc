package com.nnutc.service;

import com.nnutc.bean.User;

public interface UserService {
    public User login(String userName,String userPwd);
}
