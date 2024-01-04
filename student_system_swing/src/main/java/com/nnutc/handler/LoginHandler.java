package com.nnutc.handler;

import com.nnutc.service.UserService;
import com.nnutc.service.impl.UserServiceImpl;
import com.nnutc.view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;


public class LoginHandler implements ActionListener {

    UserService userService = new UserServiceImpl();

    LoginView loginView;


    public LoginHandler(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login() {
        String username = loginView.getUsernameTxt();
        String password = loginView.getPasswordTxt();
        System.out.println("用户名: " + username + " " + "密码: " + password);
//        判断用户合法性
        if (username == null || "".equals(username.trim()) || password == null || "".equals(password.trim())) {
            JOptionPane.showMessageDialog(loginView, "用户名或密码为空！");
        } else {
            userService.login(loginView, username, password);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jbutton = (JButton) e.getSource();
        String text = jbutton.getText();
        if ("重置".equals(text)) {
            System.out.println("重置");
//            loginView.getUsernameTxt().setText("");
//            loginView.getPasswordTxt().setText("");
        } else if ("登录".equals(text)) {
            System.out.println("登录");
            login();
        }
    }
}
