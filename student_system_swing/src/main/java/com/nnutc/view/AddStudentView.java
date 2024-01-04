package com.nnutc.view;

import com.nnutc.bean.Student;
import com.nnutc.common.ResultVO;
import com.nnutc.handler.AddUpdateHandler;
import com.nnutc.utils.*;
import com.nnutc.view.ext.MainViewTable;
import com.nnutc.view.ext.MainViewTableModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.util.*;


@Data
@AllArgsConstructor
public class AddStudentView extends JDialog {
    JPanel jPanel;
    JLabel nameLabel;
    JTextField nameTxt;
    JLabel noLabel;
    JTextField noTxt;
    JLabel genderLabel;
    JTextField genderTxt;
    JButton addButton;
    AddUpdateHandler addUpdateHandler;
    MainViewTable mainViewTable;
    boolean isUpdate;

    public AddStudentView(MainView mainView) {
        super(mainView, "添加学生", true);
        setSize(350, 300);
        mainViewTable = mainView.getMainViewTable();
        addUpdateHandler = new AddUpdateHandler(mainView,this);
        jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));

        noLabel = new JLabel("学号");
        nameLabel = new JLabel("姓名");
        genderLabel = new JLabel("性别");
        if (mainView.getSelectedStu()!=null){
            isUpdate = true;
            noTxt = new JTextField(mainView.getSelectedStu().getStuId().toString());
            nameTxt = new JTextField(mainView.getSelectedStu().getStuName());
            genderTxt = new JTextField(String.valueOf(mainView.getSelectedStu().getStuGender()));
        }else{
            noTxt = new JTextField();
            nameTxt = new JTextField();
            genderTxt = new JTextField();
        }
        noLabel.setPreferredSize(new Dimension(80, 30));
        noTxt.setPreferredSize(new Dimension(200, 30));
        nameLabel.setPreferredSize(new Dimension(80, 30));
        nameTxt.setPreferredSize(new Dimension(200, 30));
        genderLabel.setPreferredSize(new Dimension(80, 30));
        genderTxt.setPreferredSize(new Dimension(200, 30));

        addButton = new JButton("提交");
        addButton.addActionListener(addUpdateHandler);


        jPanel.add(noLabel);
        jPanel.add(noTxt);
        jPanel.add(nameLabel);
        jPanel.add(nameTxt);
        jPanel.add(genderLabel);
        jPanel.add(genderTxt);
        jPanel.add(addButton);

        getContentPane().add(jPanel);
        setLocationRelativeTo(null);
//        只销毁当前窗体
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public Student getStuInfo() {
        String stuId = noTxt.getText();
        String stuName = nameTxt.getText();
        String stuGender = genderTxt.getText();
        Student student=null;
        if (stuId != null && !stuId.isEmpty()) {
            student = new Student(Integer.parseInt(stuId), stuName, stuGender.charAt(0));
        } else if (stuId.isEmpty() && !stuName.isEmpty()&&!stuGender.isEmpty()){
            student = new Student(null, stuName, stuGender.charAt(0));
        }
        return student;
    }

    public void insertStu(Student student) {
        Map<String, String> params = new HashMap<>();
        if (student.getStuId() != null) {
            params.put("stuId", student.getStuId().toString());
        } else {
            params.put("stuId", "null");
        }
        params.put("stuName", student.getStuName());
        params.put("stuGender", Character.toString(student.getStuGender()));
        //        访问接口
        PostUtils.postWithJson("http://localhost:8080/stu/addStu", params, new SuccessListener() {
            @Override
            public void success(String result) {
                ResultVO resultVO = JsonUtils.parseResult(result);
                if (resultVO.getCode() == 10000) {
                    System.out.println(result);
                }
            }
        }, new FailListener() {
            @Override
            public void fail() {
                System.out.println("fail");
            }
        });
    }
    public void updateStu(Student student) {
        Map<String, String> params = new HashMap<>();
        if (student.getStuId() != null) {
            params.put("stuId", student.getStuId().toString());
        } else {
            params.put("stuId", "null");
        }
        params.put("stuName", student.getStuName());
        params.put("stuGender", Character.toString(student.getStuGender()));
        //        访问接口
        PostUtils.postWithJson("http://localhost:8080/stu/updateStu", params, new SuccessListener() {
            @Override
            public void success(String result) {
                ResultVO resultVO = JsonUtils.parseResult(result);
                if (resultVO.getCode() == 10000) {
                    System.out.println(result);
                }
            }
        }, new FailListener() {
            @Override
            public void fail() {
                System.out.println("fail");
            }
        });
    }
}
