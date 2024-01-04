package com.nnutc.view;

import com.nnutc.common.ResultVO;
import com.nnutc.component.BackGroundPanel;
import com.nnutc.utils.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RegisterInterface {
    JFrame jFrame = new JFrame("注册");
    final int WIDTH = 500;
    final int HEIGHT = 300;

    //    组装视图
    public void init() throws IOException {
//        设置窗口属性
        jFrame.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
        jFrame.setResizable(false);

        jFrame.setIconImage(ImageIO.read(new File("C:/Users/admin/Documents/code/Java/student_swing/target/classes/imgs/logo.png")));
        BackGroundPanel backGroundPanel = new BackGroundPanel(ImageIO.read(new File("C:/Users/admin/Documents/code/Java/student_swing/target/classes/imgs/cat.jpg")));


        Box verticalBox = Box.createVerticalBox();
        //        组装用户名
        Box usernameBox = Box.createHorizontalBox();
        JLabel userLabel = new JLabel("用户名：");
        JTextField usernameField = new JTextField(15);
        usernameBox.add(userLabel);
        usernameBox.add(Box.createHorizontalStrut(20));
        usernameBox.add(usernameField);

        //        组装密码
        Box passwordBox = Box.createHorizontalBox();
        JLabel passwordLabel = new JLabel("密    码：");
        JTextField passwordField = new JTextField(15);
        passwordBox.add(passwordLabel);
        passwordBox.add(Box.createHorizontalStrut(20));
        passwordBox.add(passwordField);

//        组装按钮
        Box buttonBox = Box.createHorizontalBox();
        JButton registerButton = new JButton("注册");
        JButton backLoginButton = new JButton("返回登录");

        registerButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
//                        获取注册用户信息
                        String username = usernameField.getText().trim();
                        String password = passwordField.getText().trim();
                        Map<String, String> params = new HashMap<>();

                        params.put("userName", username);
                        params.put("userPwd", password);
//                        访问后台接口
                        PostUtils.postWithJson("http://localhost:8080/user/register", params, new SuccessListener() {
                            @Override
                            public void success(String result) {
                                ResultVO resultVO = JsonUtils.parseResult(result);
                                System.out.println(resultVO);
                                if (resultVO.getCode()==10000){
                                    JOptionPane.showMessageDialog(jFrame, resultVO.getMsg());
                                }else{
                                    JOptionPane.showMessageDialog(jFrame, resultVO.getMsg());
                                }
                            }
                        }, new FailListener() {
                            @Override
                            public void fail() {
                                JOptionPane.showMessageDialog(jFrame, "网络异常");
                            }
                        });
                    }
                }
        );
        backLoginButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            new MainInterface().init();
                            jFrame.dispose();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        buttonBox.add(registerButton);
        buttonBox.add(Box.createHorizontalStrut(80));
        buttonBox.add(backLoginButton);


        verticalBox.add(Box.createVerticalStrut(50));
        verticalBox.add(usernameBox);
        verticalBox.add(Box.createVerticalStrut(20));
        verticalBox.add(passwordBox);
        verticalBox.add(Box.createVerticalStrut(20));
        verticalBox.add(buttonBox);

        backGroundPanel.add(verticalBox);
        jFrame.add(backGroundPanel);

        jFrame.setVisible(true);
    }
}
