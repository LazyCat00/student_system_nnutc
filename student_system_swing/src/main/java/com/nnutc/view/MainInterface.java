package com.nnutc.view;

import com.nnutc.common.ResultVO;
import com.nnutc.component.BackGroundPanel;
import com.nnutc.test.Test;
import com.nnutc.utils.*;
import com.nnutc.view.ext.ManagerInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainInterface {
    JFrame jFrame = new JFrame("学生信息管理系统");
    final int WIDTH = 500;
    final int HEIGHT = 300;

    //    组装视图
    public void init() throws IOException {
//        设置窗口相关属性（坐标及长宽）
        jFrame.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
//        固定窗口
        jFrame.setResizable(false);
//        设置窗口图标
//        getClassLoader().getResource()定位到项目的target/classes目录下的资源：也就是resources目录下
        URL imgUrl = MainInterface.class.getClassLoader().getResource("imgs/logo.png");
        jFrame.setIconImage(ImageIO.read(new File("C:/Users/admin/Documents/code/Java/student_swing/target/classes/imgs/logo.png")));

//        设置窗口内容

        URL backgroundImgUrl = MainInterface.class.getClassLoader().getResource("imgs/bg.jpg");
        BackGroundPanel backGroundPanel = new BackGroundPanel(ImageIO.read(new File("C:/Users/admin/Documents/code/Java/student_swing/target/classes/imgs/cat.jpg")));

//        组装登录相关内容
        Box verticalBox = Box.createVerticalBox();
//        组装用户名
        Box usernameBox = Box.createHorizontalBox();
        JLabel usernameLabel = new JLabel("用户名：");
        JTextField usernameField = new JTextField(15);
        usernameBox.add(usernameLabel);
//        间隔
        usernameBox.add(Box.createHorizontalStrut(20));
        usernameBox.add(usernameField);
//        组装密码
        Box passwordBox = Box.createHorizontalBox();
        JLabel passwordLabel = new JLabel("密    码：");
        JTextField passwordField = new JTextField(15);
        passwordBox.add(passwordLabel);
//        间隔
        passwordBox.add(Box.createHorizontalStrut(20));
        passwordBox.add(passwordField);
//组装按钮
        Box buttonBox = Box.createHorizontalBox();
        JButton loginButton = new JButton("登录");
        JButton registerButton = new JButton("注册");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                获取用户输入的数据 trim()用于删除字符串开头和结尾的空白字符
                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
//                访问登录接口
//                使用匿名内部类来创建一个实现了某个接口或类的实例，而不需要为其指定一个名字。这在你只需要使用一次该类或接口的情况下特别有用。
                PostUtils.postWithParams("http://localhost:8080/user/login", params, new SuccessListener() {
                    @Override
                    public void success(String result) {
                        ResultVO resultVO = JsonUtils.parseResult(result);
                        System.out.println(resultVO);
                        if (resultVO.getCode() == 20000) {
                            //登录成功 展示主界面
                            try {
                                new ManagerInterface().init();
                                jFrame.dispose();
                            } catch (IOException ex) {
                               ex.printStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(jFrame, "登录失败");
                        }
                    }
                }, new FailListener() {
                    @Override
                    public void fail() {
                        JOptionPane.showMessageDialog(jFrame, "网络异常");
                    }
                });

            }
        });
        registerButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            new RegisterInterface().init();
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
//                        当前界面消失
                        jFrame.dispose();
                    }
                }
        );
        buttonBox.add(loginButton);
        buttonBox.add(Box.createHorizontalStrut(100));
        buttonBox.add(registerButton);

        verticalBox.add(Box.createVerticalStrut(50));
        verticalBox.add(usernameBox);
        verticalBox.add(Box.createVerticalStrut(20));
        verticalBox.add(passwordBox);
        verticalBox.add(Box.createVerticalStrut(40));
        verticalBox.add(buttonBox);

        backGroundPanel.add(verticalBox);
        jFrame.add(backGroundPanel);
//        设置窗口可见
        jFrame.setVisible(true);
    }

    //    客户端程序的入口
    public static void main(String[] args) {
        try {
            new MainInterface().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
