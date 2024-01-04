package com.nnutc.component;


import com.nnutc.utils.PostUtils;
import com.nnutc.utils.ScreenUtils;
import com.nnutc.utils.SuccessListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class AddStudentDialog extends JDialog {
    final int WIDTH = 400;
    final int HEIGHT = 300;
    JPanel centerPanel;
    SpringLayout springLayout = new SpringLayout();
    JLabel nameLable;
    JTextField nameField;
    JLabel genderLabel;
    JButton addBtn;

    public AddStudentDialog(JFrame jf, String title, boolean isModel){
        super(jf,title,isModel);
        centerPanel.setBounds(0,0,WIDTH,HEIGHT);
        centerPanel.setLayout(springLayout);
        Container container = getContentPane();
        centerPanel.add(nameLable);
        centerPanel.add(nameField);
        centerPanel.add(genderLabel);

        container.add(centerPanel, BorderLayout.CENTER);
        setResizable(false);
        setVisible(true);
        //学生性别
//        Box genderBox = Box.createHorizontalBox();
//        JLabel genderLabel = new JLabel("学生性别：");
//        JRadioButton  maleRadioButton = new JRadioButton("Male");
//        JRadioButton femaleRadioButton = new JRadioButton("Female");
//        ButtonGroup genderButtonGroup = new ButtonGroup();
//        genderButtonGroup.add(maleRadioButton);
//        genderButtonGroup.add(femaleRadioButton);
//        maleRadioButton.setSelected(true);
//        genderBox.add(genderLabel);
//        nameBox.add(Box.createHorizontalStrut(20));
//        genderBox.add(maleRadioButton);
//        genderBox.add(Box.createHorizontalStrut(20));
//        genderBox.add(femaleRadioButton);

    }

}
