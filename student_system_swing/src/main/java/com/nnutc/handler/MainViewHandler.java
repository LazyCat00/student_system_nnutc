package com.nnutc.handler;

import com.nnutc.bean.Student;
import com.nnutc.service.StudentService;
import com.nnutc.service.impl.StudentServiceImpl;
import com.nnutc.view.AddStudentView;
import com.nnutc.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

public class MainViewHandler implements ActionListener {
    private MainView mainView;
    StudentService studentService = new StudentServiceImpl();

    public MainViewHandler(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String buttonTitle = jButton.getText();
        if ("下一页".equals(buttonTitle)) {
            LinkedHashMap page = mainView.getPage();
            Integer pageNo = (Integer) page.get("pageNo");
            Integer pageSize = (Integer) page.get("pageSize");
            String url = (String) page.get("url");
            if (url.equals("queryFuzz")) {
                mainView.queryFuzzy(mainView.getFuzz(), pageNo + 1, pageSize);
            } else if (url.equals("queryByPage")) {
                mainView.queryStuByPage(pageNo + 1, pageSize);
            }

        } else if ("上一页".equals(buttonTitle)) {
            LinkedHashMap page = mainView.getPage();
            Integer pageNo = (Integer) page.get("pageNo");
            Integer pageSize = (Integer) page.get("pageSize");
            String url = (String) page.get("url");
            if (url.equals("queryFuzz")) {
                mainView.queryFuzzy(mainView.getFuzz(), pageNo - 1, pageSize);
            } else if (url.equals("queryByPage")) {
                mainView.queryStuByPage(pageNo - 1, pageSize);
            }
            mainView.queryStuByPage(pageNo - 1, pageSize);
        } else if ("查询".equals(buttonTitle)) {
            String text = mainView.getSearchTxt().getText();
            mainView.setFuzz(text);
            mainView.queryFuzzy(text, 1, 4);
        } else if ("新增".equals(buttonTitle)) {
            mainView.setSelectedStu(null);
            new AddStudentView(mainView);

        } else if ("修改".equals(buttonTitle)) {
            Student selectedStuInfo = mainView.getSelectedStuInfo();
            if (selectedStuInfo!=null){
                mainView.setSelectedStu(selectedStuInfo);
                new AddStudentView(mainView);
            }
        } else if ("删除".equals(buttonTitle)) {
            Student selectedStuInfo = mainView.getSelectedStuInfo();
            if (selectedStuInfo!=null){
                mainView.setSelectedStu(selectedStuInfo);
                mainView.deleteStu(mainView.getSelectedStu().getStuId());
                mainView.queryStuByPage((Integer) mainView.getPage().get("pageNo"),(Integer) mainView.getPage().get("pageSize"));
            }
        }
    }
}
