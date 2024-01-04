package com.nnutc.dao;

import com.nnutc.bean.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {
    public User findUserByName(String userName);
    public int insertUser(User user);
}
