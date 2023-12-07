package com.nnutc.dao;

import com.nnutc.bean.User;

public interface UserDAO {
    public User findUserByName(String userName);
}
