package com.nnutc.service.impl;

import com.nnutc.bean.User;
import com.nnutc.dao.UserDAO;
import com.nnutc.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDAO userDAO;

    @Override
    public User login(String userName, String userPwd) {
        User user = userDAO.findUserByName(userName);
        if (user != null) {
            if (user.getUserPwd().equals(userPwd)) {
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
