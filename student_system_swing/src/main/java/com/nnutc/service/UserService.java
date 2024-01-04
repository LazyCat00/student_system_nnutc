package com.nnutc.service;

import com.nnutc.bean.User;
import com.nnutc.common.ResultVO;
import com.nnutc.view.LoginView;

import java.awt.*;

public interface UserService {
    public void login(LoginView loginView,String username, String password);
}
