package com.nnutc.view;

import com.nnutc.utils.DimensionUtil;
import com.nnutc.view.ext.MainViewTable;
import com.nnutc.view.ext.MainViewTableModel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Vector;

public class MainView extends JFrame {
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));//默认使用流布局
    JButton addButton = new JButton("增加");
    JButton updateButton = new JButton("修改");
    JButton deleteButton = new JButton("删除");
    JTextField searchTxt = new JTextField(15);
    JButton searchButton = new JButton("查询");
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preButton = new JButton("上一页");
    JButton nextButton = new JButton("下一页");
    MainViewTable mainViewTable = new MainViewTable();

    public MainView() {
        super("学生管理系统");
        Container contentPane = getContentPane();
//        放置北边的组件
        layoutNorth(contentPane);
//        设置中间的jTable
        layoutCenter(contentPane);
//        放置南边的组件
        layoutSouth(contentPane);


        //        自定义图标
        URL imgUrl = LoginView.class.getClassLoader().getResource("logo.jpg");
        setIconImage(new ImageIcon(imgUrl).getImage());
//        根据屏幕大小设置主界面大小
        setBounds(DimensionUtil.getBounds());
//        设置窗体完全充满整个屏幕可见大小
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    private void layoutCenter(Container contentPane) {
        Vector<Vector<Object>> data = new Vector<>();

        Vector<Object> rowVector1 = new Vector<>();
        rowVector1.addElement("1");
        rowVector1.addElement("张三");
        rowVector1.addElement("no1");
        rowVector1.addElement("甘肃");
        rowVector1.addElement("1");
        rowVector1.addElement("2");
        rowVector1.addElement("3");
        rowVector1.addElement("6");

        Vector<Object> rowVector2 = new Vector<>();
        rowVector2.addElement("2");
        rowVector2.addElement("李四");
        rowVector2.addElement("no2");
        rowVector2.addElement("甘肃");
        rowVector2.addElement("1");
        rowVector2.addElement("2");
        rowVector2.addElement("3");
        rowVector2.addElement("6");

        Vector<Object> rowVector3 = new Vector<>();
        rowVector3.addElement("3");
        rowVector3.addElement("汪大东");
        rowVector3.addElement("no3");
        rowVector3.addElement("甘肃");
        rowVector3.addElement("1");
        rowVector3.addElement("2");
        rowVector3.addElement("3");
        rowVector3.addElement("6");

        data.addElement(rowVector1);
        data.addElement(rowVector2);
        data.addElement(rowVector3);
        MainViewTableModel mainViewTableModel =  MainViewTableModel.assembleModel(data);
        mainViewTable.setModel(mainViewTableModel);
        mainViewTable.renderRuler();
        JScrollPane jScrollPane = new JScrollPane(mainViewTable);
        contentPane.add(jScrollPane,BorderLayout.CENTER);
    }

    private void layoutNorth(Container contentPane) {
        northPanel.add(addButton);
        northPanel.add(updateButton);
        northPanel.add(deleteButton);
        northPanel.add(searchTxt);
        northPanel.add(searchButton);
        contentPane.add(northPanel, BorderLayout.NORTH);
    }

    private void layoutSouth(Container contentPane) {
        southPanel.add(preButton);
        southPanel.add(nextButton);
        contentPane.add(southPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new MainView();
    }
}
