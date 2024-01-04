package com.nnutc.handler;

import com.nnutc.bean.Student;
import com.nnutc.view.AddStudentView;
import com.nnutc.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUpdateHandler implements ActionListener {
    private AddStudentView addStudentView;
    private MainView mainView;

    public AddUpdateHandler(MainView mainView,AddStudentView addStudentView) {
        this.addStudentView = addStudentView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String txt = jButton.getText();
        if ("提交".equals(txt)) {
            Student student = addStudentView.getStuInfo();
            if (student != null) {
                if (addStudentView.isUpdate()) {
                    System.out.println("更新");
                    addStudentView.updateStu(student);
                    addStudentView.dispose();
                    System.out.println(mainView.getPage().get("pageNo"));
                    mainView.queryStuByPage((Integer)mainView.getPage().get("pageNo"),(Integer) mainView.getPage().get("pageSize"));
                } else {
                    System.out.println("新增");
                    addStudentView.insertStu(student);
                    addStudentView.dispose();
                    mainView.queryStuByPage((Integer)mainView.getPage().get("pageTotal"),(Integer) mainView.getPage().get("pageSize"));
                }

            }
        }
    }
}
