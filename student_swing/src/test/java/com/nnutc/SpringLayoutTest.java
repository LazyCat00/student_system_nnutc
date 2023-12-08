package com.nnutc;

import javax.swing.*;
import java.awt.*;

public class SpringLayoutTest extends JFrame {
    //    设置jpanel的布局管理器为SpringLayout
    SpringLayout springLayout = new SpringLayout();
    JPanel jPanel = new JPanel(springLayout);
    JLabel titleLabel = new JLabel("文章标题");
    JTextField titleTxt = new JTextField();
    JLabel authorLabel = new JLabel("作者");
    JTextField authorTxt = new JTextField();
    JLabel contentLabel = new JLabel("请输入内容");
    JTextArea contentArea = new JTextArea(4, 10);

    public SpringLayoutTest() {
        super("弹簧布局SpringLayout");
        Container container = getContentPane();
//        加入到jpanel中
        jPanel.add(titleLabel);
        titleTxt.setPreferredSize(new Dimension(200, 30));
        jPanel.add(titleTxt);
        jPanel.add(authorLabel);
        authorTxt.setPreferredSize(new Dimension(200, 30));
        jPanel.add(authorTxt);
        jPanel.add(contentLabel);
        jPanel.add(contentArea);

        Spring titleLabelWidth = Spring.width(titleLabel);
        Spring titleTxtWidth = Spring.width(titleTxt);
        Spring space = Spring.constant(20);
        Spring midWidth = Spring.sum(Spring.sum(titleLabelWidth, titleTxtWidth), space);
        int offset = midWidth.getValue() / 2;


//        SpringLayout: 布局管理器
//        SpringLayout.Constraints: 使用弹簧布局的容器里的组件的布局约束,每个组件对应一个
//        Spring: 可以理解为一个可以进行四则运算的整数
        SpringLayout.Constraints titleLabbelConstraints = springLayout.getConstraints(titleLabel);
        springLayout.putConstraint(SpringLayout.WEST, titleLabel, -offset, SpringLayout.HORIZONTAL_CENTER, jPanel);
        titleLabbelConstraints.setY(Spring.constant(50));
//        设置约束的第一种写法，比较复杂
        SpringLayout.Constraints titleTxtConstraints = springLayout.getConstraints(titleTxt);
        Spring titleLabelConstraintsEastSpring = titleLabbelConstraints.getConstraint(SpringLayout.EAST);
//        titleTxt西边距离titleLabel的东边20px，北边相同
        titleTxtConstraints.setConstraint(SpringLayout.WEST, Spring.sum(titleLabelConstraintsEastSpring, Spring.constant(20)));
        titleTxtConstraints.setConstraint(SpringLayout.NORTH, titleLabbelConstraints.getConstraint(SpringLayout.NORTH));

//        设置约束的第二种写法，相对简单
//        e1: 要设置组件的哪个边界(edgeName)
//        c1: 要设置的组件
//        pad: 距离值
//        e2: 参照的组件的边界名
//        c2: 参考物(组件)

//        设置authorLabel
//        authorLabel东边和titleLabel对齐
//        authorLabel北边距离titleLabel南边20px
        springLayout.putConstraint(SpringLayout.EAST, authorLabel, 0, SpringLayout.EAST, titleLabel);
        springLayout.putConstraint(SpringLayout.NORTH, authorLabel, 20, SpringLayout.SOUTH, titleLabel);

//        设置authorTxt
//        authorTxt北边和authorLabel对齐
//        authorTxt西边距离authorLabel东边20px
        springLayout.putConstraint(SpringLayout.NORTH, authorTxt, 0, SpringLayout.NORTH, authorLabel);
        springLayout.putConstraint(SpringLayout.WEST, authorTxt, 20, SpringLayout.EAST, authorLabel);

//        设置contentLabel
//        contentLabel东边和authorLabel对齐
//        contentLabel北边距离authorLabel南边20px
        springLayout.putConstraint(SpringLayout.EAST, contentLabel, 0, SpringLayout.EAST, authorLabel);
        springLayout.putConstraint(SpringLayout.NORTH, contentLabel, 20, SpringLayout.SOUTH, authorLabel);

//        设置contentArea
//        contentArea北边和contentLabel对齐
//        contentArea西边距离contentLabel东边20px
        springLayout.putConstraint(SpringLayout.NORTH, contentArea, 0, SpringLayout.NORTH, contentLabel);
        springLayout.putConstraint(SpringLayout.WEST, contentArea, 20, SpringLayout.EAST, contentLabel);
//        设置contentArea的东边和南边（参照jPanel）
        springLayout.putConstraint(SpringLayout.EAST,contentArea,-20,SpringLayout.EAST,jPanel);
        springLayout.putConstraint(SpringLayout.SOUTH,contentArea,-20,SpringLayout.SOUTH,jPanel);
        container.add(jPanel);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SpringLayoutTest();
    }


}
