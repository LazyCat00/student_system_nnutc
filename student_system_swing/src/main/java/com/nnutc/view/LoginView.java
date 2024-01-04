package com.nnutc.view;

import com.nnutc.component.BackGroundPanel;
import com.nnutc.handler.LoginHandler;
import com.nnutc.utils.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

public class LoginView extends JFrame {
    //    文字水平对齐方式居中
    JLabel jLabel = new JLabel("学生管理系统");
    SpringLayout springLayout = new SpringLayout();
    JPanel root;
    JLabel usernameLabel = new JLabel("用户名: ");
    JTextField usernameTxt = new JTextField();
    JLabel passwordLabel = new JLabel("密    码: ");
    JTextField passwordTxt = new JTextField();
    JButton loginButton = new JButton("登录");
    JButton resetButton = new JButton("重置");

    LoginHandler loginHandler;
    //    系统托盘
    SystemTray systemTray;
    //    托盘图标
    TrayIcon trayIcon;

    public LoginView(String title, URL url) {
        super(title);
        root = new JPanel(springLayout);

        Font font = new Font("华文行楷", Font.PLAIN, 20);
        Font jLableFont = new Font("华文行楷", Font.BOLD, 30);

        jLabel.setFont(jLableFont);
        jLabel.setOpaque(true);  //设置背景颜色为不透明
        usernameLabel.setFont(font);
        usernameLabel.setOpaque(true);
        usernameTxt.setPreferredSize(new Dimension(200, 30));
        passwordLabel.setFont(font);
        passwordLabel.setOpaque(true);
        passwordTxt.setPreferredSize(new Dimension(200, 30));
        loginButton.setFont(font);
        resetButton.setFont(font);
        loginHandler = new LoginHandler(this);
        loginButton.addActionListener(loginHandler);
        springLayoutSetting();
        addComponent();

        SystemTraySetting();

        setIconImage(new ImageIcon(url).getImage());
        this.getContentPane().add(root, BorderLayout.CENTER);
    }

    public  String getUsernameTxt() {
        return usernameTxt.getText();
    }

    public String getPasswordTxt() {
        return passwordTxt.getText();
    }

    private void SystemTraySetting() {
//        判断系统是否支持托盘
        if (SystemTray.isSupported()) {
            systemTray = SystemTray.getSystemTray();
            URL imgUrl = LoginView.class.getClassLoader().getResource("logo.png");
            trayIcon = new TrayIcon(new ImageIcon(imgUrl).getImage());
//            设置托盘图片自动缩放
            trayIcon.setImageAutoSize(true);
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
//            增加最小化时销毁资源
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent e) {
                    LoginView.this.dispose();
                }
            });
//            托盘监听事件
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int clickCount = e.getClickCount();
                    if (clickCount == 1) {
                        LoginView.this.setExtendedState(JFrame.NORMAL);
                    }
//                    类名.this明确指定访问的是外部类的成员
                    LoginView.this.setVisible(true);
                }
            });
        }
    }

    private void springLayoutSetting() {
        //        弹簧布局
        Spring usernameLabelWidth = Spring.width(usernameLabel);
        Spring usernameTxtWidth = Spring.width(usernameTxt);
        Spring space = Spring.constant(20);
        Spring midWidth = Spring.sum(Spring.sum(usernameLabelWidth, usernameTxtWidth), space);
        int offset = midWidth.getValue() / 2;

        Spring jLabelWidth = Spring.width(jLabel);
        int jLableoffset = jLabelWidth.getValue() / 2;


        springLayout.putConstraint(SpringLayout.WEST, jLabel, -jLableoffset, SpringLayout.HORIZONTAL_CENTER, root);
        springLayout.putConstraint(SpringLayout.NORTH, jLabel, 20, SpringLayout.NORTH, root);

        springLayout.putConstraint(SpringLayout.WEST, usernameLabel, -offset, SpringLayout.HORIZONTAL_CENTER, root);
        springLayout.putConstraint(SpringLayout.NORTH, usernameLabel, 40, SpringLayout.SOUTH, jLabel);

//        usernameTxt开始布局
        springLayout.putConstraint(SpringLayout.WEST, usernameTxt, 20, SpringLayout.EAST, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, usernameTxt, 0, SpringLayout.NORTH, usernameLabel);

//        passwordLabel开始布局
        springLayout.putConstraint(SpringLayout.EAST, passwordLabel, 0, SpringLayout.EAST, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 20, SpringLayout.SOUTH, usernameLabel);

//        passwordTxt开始布局
        springLayout.putConstraint(SpringLayout.NORTH, passwordTxt, 0, SpringLayout.NORTH, passwordLabel);
        springLayout.putConstraint(SpringLayout.WEST, passwordTxt, 20, SpringLayout.EAST, passwordLabel);
//        loginButton开始布局
        springLayout.putConstraint(SpringLayout.WEST, loginButton, 50, SpringLayout.WEST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, loginButton, 40, SpringLayout.SOUTH, passwordLabel);
//        resetButton开始布局
        springLayout.putConstraint(SpringLayout.WEST, resetButton, 50, SpringLayout.EAST, loginButton);
        springLayout.putConstraint(SpringLayout.NORTH, resetButton, 0, SpringLayout.NORTH, loginButton);
    }

    private void addComponent(){
        root.add(jLabel);
        root.add(usernameLabel);
        root.add(usernameTxt);
        root.add(passwordLabel);
        root.add(passwordTxt);
        root.add(loginButton);
        root.add(resetButton);
    }
}
