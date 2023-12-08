package com.nnutc.handler;

import com.nnutc.bean.Admin;
import com.nnutc.service.impl.AdminServiceImpl;
import com.nnutc.view.LoginView;
import com.nnutc.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginHandler extends KeyAdapter implements ActionListener {
    private LoginView loginView;

    public LoginHandler(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login() {
        String username = loginView.getUsernameTxt().getText();
        String password = loginView.getPasswordTxt().getText();
        System.out.println("用户名: " + username + " " + "密码: " + password);
        if (username == null || "".equals(username.trim()) || password == null || "".equals(password.trim())) {
            JOptionPane.showMessageDialog(loginView, "用户名或密码为空！");
            return;
        }
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        AdminServiceImpl adminService = new AdminServiceImpl();
        boolean flag = adminService.validateAdmin(admin);
        if (flag) {
//            跳转到主界面并销毁登录界面
            new MainView();
            loginView.dispose();
        } else {
            JOptionPane.showMessageDialog(loginView, "密码错误");
        }
    }

    //    actionPerformed
//    这是接口ActionListener里面定义的一个抽象方法，所有实现这个接口的类都要重写这个方法。
//    组件发生“有意义”的事件时会调用这个方法，比如按钮被按下，文本框内输入回车时都会触发这个事件，然后调用你编写的事件处理程序。
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jbutton = (JButton) e.getSource();
        String text = jbutton.getText();
        if ("重置".equals(text)) {
            System.out.println("重置");
            loginView.getUsernameTxt().setText("");
            loginView.getPasswordTxt().setText("");
        } else if ("登录".equals(text)) {
            System.out.println("登录");
            login();
        }
    }
}
