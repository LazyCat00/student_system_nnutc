package com.nnutc.view;

import com.nnutc.component.BackGroundPanel;
import com.nnutc.handler.LoginHandler;
import com.nnutc.utils.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;

public class LoginView extends JFrame {
    //    窗口宽高
    private static final Integer FRAME_WIDTH = 500;
   private static final Integer FRAME_HEIGHT = 300;
    //    创建具有指定文本的 JLabel 标签组件
    //    文字水平对齐方式居中
    JLabel jLabel = new JLabel("学生管理系统", JLabel.CENTER);
    //    SpringLayout: 弹性布局
    //    改变窗体的大小时, 自动调整组件的大小布满整个窗体(不改变组件间相对位置)
    SpringLayout springLayout = new SpringLayout();
    //    JPanel默认的布局管理器是FlowLayout
    JPanel centerPanel;
    JLabel usernameLabel = new JLabel("用户名: ");
    JTextField usernameTxt = new JTextField();
    JLabel passwordLabel = new JLabel("密    码: ");
    JTextField passwordTxt = new JTextField();
    JButton loginButton = new JButton("登录");
    JButton resetButton = new JButton("重置");
    SystemTray systemTray;
    TrayIcon trayIcon;
    LoginHandler loginHandler;


    //    创建类类型对象时由编译器自动调用，保证每个数据成员都有 一个合适的初始值，并且在对象的生命周期内只调用一次。
//    构造函数的名称虽然叫构造，但是需要注意的是，构造函数的主要任务并不是开空间创建对象，而是初始化对象。
    public LoginView() {

//        设置显示窗口标题
        super("学生成绩管理系统");


        loginHandler = new LoginHandler(this);
//        用getContentPane()方法获得JFrame的内容面板，再对其加入组件：
        Container container = getContentPane();
        try {
            Image image = ImageIO.read(LoginView.class.getClassLoader().getResource("bg.jpg"));
            centerPanel = new BackGroundPanel(image);
            centerPanel.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
            centerPanel.setLayout(springLayout);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Font font = new Font("华文行楷", Font.PLAIN, 20);
        Font jLableFont = new Font("华文行楷", Font.BOLD, 30);
//        如果一容器使用了布局管理器，则该容器内的组件或容器（即该容器的下一级）应该使用
//        setPreferredSize(new Dimension(w,h));来设置尺寸;
        jLabel.setFont(jLableFont);
        jLabel.setOpaque(true);  //此句是重点，设置背景颜色必须先将它设置为不透明的，因为默认是透明的。。。
        usernameLabel.setFont(font);
        usernameLabel.setOpaque(true);
        usernameTxt.setPreferredSize(new Dimension(200, 30));
        passwordLabel.setFont(font);
        passwordLabel.setOpaque(true);
        passwordTxt.setPreferredSize(new Dimension(200, 30));
        loginButton.setFont(font);
        resetButton.setFont(font);
//        组件加入面板
        centerPanel.add(jLabel);
        centerPanel.add(usernameLabel);
        centerPanel.add(usernameTxt);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordTxt);


//        增加按键事件
        loginButton.addActionListener(loginHandler);
        resetButton.addActionListener(loginHandler);

        centerPanel.add(loginButton);
        centerPanel.add(resetButton);


//        弹簧布局
//        通过组件与容器边缘以及组件与组件边缘建立约束(建立位置关系)
//        计算指定组件的宽度所表示的 spring
        Spring usernameLabelWidth = Spring.width(usernameLabel);
        Spring usernameTxtWidth = Spring.width(usernameTxt);
//        创建一个指定长度的 spring
        Spring space = Spring.constant(20);
        Spring midWidth = Spring.sum(Spring.sum(usernameLabelWidth, usernameTxtWidth), space);
        int offset = midWidth.getValue() / 2;

        Spring jLabelWidth = Spring.width(jLabel);
        int jLableoffset = jLabelWidth.getValue() / 2;
//        jLabel开始布局
//        jLabel的西边 距离 centerPanel的中心点 -jLableoffset
        springLayout.putConstraint(SpringLayout.WEST, jLabel, -jLableoffset, SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, jLabel, 20, SpringLayout.NORTH, centerPanel);

//        usernameLabel开始布局
//        usernameLabel的西边 距离 centerPanel的中心点 -offset
        springLayout.putConstraint(SpringLayout.WEST, usernameLabel, -offset, SpringLayout.HORIZONTAL_CENTER, centerPanel);
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


//        container.add(jLabel, BorderLayout.NORTH);
        container.add(centerPanel, BorderLayout.CENTER);

//        判断系统是否支持托盘
        if (SystemTray.isSupported()) {
            systemTray = SystemTray.getSystemTray();
//            getClassLoader().getResource()定位到项目的target/classes目录下的资源：也就是resources目录下
            URL imgUrl = LoginView.class.getClassLoader().getResource("logo.jpg");
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
                    LoginView.this.setVisible(true);
                }
            });
        }
//        自定义图标
        URL imgUrl = LoginView.class.getClassLoader().getResource("logo.jpg");
        setIconImage(new ImageIcon(imgUrl).getImage());

//        设置窗口显示尺寸
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
//        JFrame在屏幕居中显示
        setLocationRelativeTo(null);
//        置窗口是否可以关闭，关闭窗口后程序将一起关闭
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginView();
    }

    public JTextField getUsernameTxt() {
        return usernameTxt;
    }

    public JTextField getPasswordTxt() {
        return passwordTxt;
    }

}
