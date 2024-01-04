package com.nnutc.view.ext;

import com.nnutc.component.StudentManageComponent;
import com.nnutc.utils.ScreenUtils;
import com.nnutc.view.MainInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ManagerInterface {

    JFrame jFrame = new JFrame("nnutc学生管理系统: XXX欢迎您");
    final int WIDTH = 1000;
    final int HEIGHT = 600;

    //    组装视图
    public void init() throws IOException {
//        设置窗口相关属性（坐标及长宽）
        jFrame.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
//        固定窗口
        jFrame.setResizable(false);

        jFrame.setIconImage(ImageIO.read(new File("C:/Users/admin/Documents/code/Java/student_swing/target/classes/imgs/logo.png")));

//        设置菜单栏
        JMenuBar jMenuBar = new JMenuBar();
        JMenu settingMenu = new JMenu("设置");
        JMenuItem changeItemMenu = new JMenuItem("切换账号");
        JMenuItem exitItemMenu = new JMenuItem("退出程序");
        changeItemMenu.addActionListener(
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
        exitItemMenu.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                }
        );
        settingMenu.add(changeItemMenu);
        settingMenu.add(exitItemMenu);
        jMenuBar.add(settingMenu);


//        设置分割面板
        JSplitPane jSplitPane = new JSplitPane();
//        支持连续布局
        jSplitPane.setContinuousLayout(true);
//        设置分割面板位置
        jSplitPane.setDividerLocation(150);
//        设置分割面板尺寸
        jSplitPane.setDividerSize(7);
//        设置左侧内容
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("系统管理");
        DefaultMutableTreeNode studentManage = new DefaultMutableTreeNode("学生管理");
        DefaultMutableTreeNode scoreManage = new DefaultMutableTreeNode("成绩管理");
        DefaultMutableTreeNode totalScoreManage = new DefaultMutableTreeNode("学生总成绩管理");

        root.add(studentManage);
        root.add(scoreManage);
        root.add(totalScoreManage);

        JTree jTree = new JTree(root);
        MyRenderer myRenderer = new MyRenderer();
        jTree.setCellRenderer(myRenderer);
        Color color = new Color(203, 220, 217);
        myRenderer.setBackgroundNonSelectionColor(color);
        myRenderer.setBackgroundSelectionColor(new Color(140, 140, 140));
        jTree.setBackground(color);
//        设置当前tree默认选中成绩管理
        jTree.setSelectionRow(1);
        jTree.addTreeSelectionListener(new TreeSelectionListener() {
            //            当条目选中变化后执行
            @Override
            public void valueChanged(TreeSelectionEvent e) {
//                得到当前选中的结点对象
                Object lastPathComponent = e.getNewLeadSelectionPath().getLastPathComponent();
                if (studentManage.equals(lastPathComponent)) {
                    jSplitPane.setRightComponent(new StudentManageComponent(jFrame));
                } else if (scoreManage.equals(lastPathComponent)) {
                    jSplitPane.setRightComponent(new JLabel("这里进行成绩管理"));
                } else if (totalScoreManage.equals(lastPathComponent)) {
                    jSplitPane.setRightComponent(new JLabel("这里进行学生总成绩管理"));
                }
            }
        });

        jSplitPane.setRightComponent(new StudentManageComponent(jFrame));
        jSplitPane.setLeftComponent(jTree);
        jFrame.setJMenuBar(jMenuBar);
        jFrame.add(jSplitPane);
        jFrame.setVisible(true);
    }


    //    自定义结点绘制器
    public class MyRenderer extends DefaultTreeCellRenderer {
        private ImageIcon rootIcon = null;
        private ImageIcon studentManageIcon = null;
        private ImageIcon scoreManageIcon = null;
        private ImageIcon totalScoreManageIcon = null;

        public MyRenderer() {
            rootIcon = new ImageIcon("C:/Users/admin/Documents/code/github/java/student_system_nnutc/student_system_swing/target/classes/imgs/systemManage.png");
            studentManageIcon = new ImageIcon("C:/Users/admin/Documents/code/github/java/student_system_nnutc/student_system_swing/target/classes/imgs/userManage.png");
            scoreManageIcon = new ImageIcon("C:/Users/admin/Documents/code/github/java/student_system_nnutc/student_system_swing/target/classes/imgs/bookManage.png");
            totalScoreManageIcon = new ImageIcon("C:/Users/admin/Documents/code/github/java/student_system_nnutc/student_system_swing/target/classes/imgs/borrowManage.png");
        }

        //    当绘制树的每个结点时，都会调用这个方法
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            ImageIcon image = null;
            switch (row) {
                case 0:
                    image = rootIcon;
                    break;
                case 1:
                    image = studentManageIcon;
                    break;
                case 2:
                    image = scoreManageIcon;
                    break;
                case 3:
                    image = totalScoreManageIcon;
                    break;
            }
            this.setIcon(image);
            return this;
        }
    }

    public static void main(String[] args) {
        try {
            new ManagerInterface().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
