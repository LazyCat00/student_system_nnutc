package com.nnutc.service.impl;

import com.nnutc.common.ResultVO;
import com.nnutc.service.UserService;
import com.nnutc.utils.FailListener;
import com.nnutc.utils.JsonUtils;
import com.nnutc.utils.PostUtils;
import com.nnutc.utils.SuccessListener;
import com.nnutc.view.LoginView;
import com.nnutc.view.MainView;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;


public class UserServiceImpl implements UserService {
    @Override
    public void login(LoginView loginView,String username, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        //        访问登录接口
        PostUtils.postWithParams("http://localhost:8080/user/login", params, new SuccessListener() {
            @Override
            public void success(String result) {
                ResultVO resultVO = JsonUtils.parseResult(result);
                System.out.println(resultVO);
                if (resultVO.getCode() == 20000) {
                    //登录成功 展示主界面
                    try {
                        new MainView();
                        loginView.dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(loginView, "登录失败");
                }
            }
        }, new FailListener() {
            @Override
            public void fail() {
                JOptionPane.showMessageDialog(loginView, "网络异常");
            }
        });
    }
}
