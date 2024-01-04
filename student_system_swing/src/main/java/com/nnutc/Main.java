package com.nnutc;

import com.nnutc.view.LoginView;
import javax.swing.*;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        URL imgUrl = LoginView.class.getClassLoader().getResource("logo.png");
        JFrame loginView = new LoginView("登录", imgUrl);
        loginView.setSize(600, 400);
        loginView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        窗口居中
        loginView.setLocationRelativeTo(null);
//        允许用户调整myFrame窗口的大小
        loginView.setResizable(true);
        loginView.setVisible(true);
    }
}