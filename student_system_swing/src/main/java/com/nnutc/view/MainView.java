package com.nnutc.view;


import com.nnutc.bean.Student;
import com.nnutc.common.ResultVO;
import com.nnutc.handler.MainViewHandler;
import com.nnutc.utils.*;
import com.nnutc.view.ext.MainViewTable;
import com.nnutc.view.ext.MainViewTableModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.*;


@Data
@AllArgsConstructor
public class MainView extends JFrame {
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));//默认使用流布局
    JButton addButton = new JButton("新增");
    JButton updateButton = new JButton("修改");
    JButton deleteButton = new JButton("删除");
    JTextField searchTxt = new JTextField(15);
    JButton searchButton = new JButton("查询");
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preButton = new JButton("上一页");
    JButton nextButton = new JButton("下一页");
    MainViewTable mainViewTable = new MainViewTable();
    MainViewHandler mainViewHandler;
    String fuzz;
    LinkedHashMap page;
    Vector<Vector<Object>> data;
    Student selectedStu;

    public MainView() {
        super("学生管理系统");
        mainViewHandler = new MainViewHandler(this);
        Container contentPane = getContentPane();
//        放置北边的组件
        layoutNorth(contentPane);
//        设置中间的jTable
        layoutCenter(contentPane);
//        放置南边的组件
        layoutSouth(contentPane);


        //        自定义图标
        URL imgUrl = LoginView.class.getClassLoader().getResource("logo.png");
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

    private void layoutNorth(Container contentPane) {
        searchButton.addActionListener(mainViewHandler);
        addButton.addActionListener(mainViewHandler);
        updateButton.addActionListener(mainViewHandler);
        deleteButton.addActionListener(mainViewHandler);
        northPanel.add(addButton);
        northPanel.add(updateButton);
        northPanel.add(deleteButton);
        northPanel.add(searchTxt);
        northPanel.add(searchButton);
        contentPane.add(northPanel, BorderLayout.NORTH);
    }

    private void layoutCenter(Container contentPane) {
        if (page==null){
            queryStuByPage(1, 4);
        }
        MainViewTableModel mainViewTableModel = MainViewTableModel.assembleModel(data);
        mainViewTable.setModel(mainViewTableModel);
        mainViewTable.renderRuler();
        JScrollPane jScrollPane = new JScrollPane(mainViewTable);
        contentPane.add(jScrollPane, BorderLayout.CENTER);
    }

    private void layoutSouth(Container contentPane) {
        nextButton.addActionListener(mainViewHandler);
        preButton.addActionListener(mainViewHandler);
        southPanel.add(preButton);
        southPanel.add(nextButton);
        contentPane.add(southPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new MainView();
    }

    public void queryStuByPage(Integer pageNo, Integer pageSize) {
        Map<String, String> params = new HashMap<>();
        params.put("pageNo", pageNo.toString());
        params.put("pageSize", pageSize.toString());
        //        访问接口
        PostUtils.postWithParams("http://localhost:8080/stu/queryByPage", params, new SuccessListener() {
            @Override
            public void success(String result) {
                ResultVO resultVO = JsonUtils.parseResult(result);
                if (resultVO.getCode() == 10000) {
                    Vector<Vector> vectors = new Vector<>();
                    page = (LinkedHashMap) resultVO.getData();
                    ArrayList stuList = (ArrayList) page.get("items");
                    for (int i = 0; i < stuList.size(); i++) {
                        HashMap map = (HashMap) stuList.get(i);
                        Vector vector = new Vector();
                        vector.addAll(map.values());
                        vectors.add(vector);
                    }
                    data = VectorUtil.convertVectorOfVectorsToVectorOfObjectVectors(vectors);
                    mainViewTable.setModel(MainViewTableModel.assembleModel(data));
                    mainViewTable.renderRuler();
                }
            }
        }, new FailListener() {
            @Override
            public void fail() {
                System.out.println("fail");
            }
        });
    }

    public void queryFuzzy(String fuzz, Integer pageNo, Integer pageSize) {
        Map<String, String> params = new HashMap<>();
        params.put("fuzz", fuzz);
        params.put("pageNo", pageNo.toString());
        params.put("pageSize", pageSize.toString());
        //        访问接口
        PostUtils.postWithParams("http://localhost:8080/stu/queryFuzz", params, new SuccessListener() {
            @Override
            public void success(String result) {
                ResultVO resultVO = JsonUtils.parseResult(result);
                if (resultVO.getCode() == 10000) {
                    Vector<Vector> vectors = new Vector<>();
                    page = (LinkedHashMap) resultVO.getData();
                    ArrayList stuList = (ArrayList) page.get("items");
                    for (int i = 0; i < stuList.size(); i++) {
                        HashMap map = (HashMap) stuList.get(i);
                        Vector vector = new Vector();
                        vector.addAll(map.values());
                        vectors.add(vector);
                    }
                    data = VectorUtil.convertVectorOfVectorsToVectorOfObjectVectors(vectors);
                    mainViewTable.setModel(MainViewTableModel.assembleModel(data));
                    mainViewTable.renderRuler();
                }
            }
        }, new FailListener() {
            @Override
            public void fail() {
                System.out.println("fail");
            }
        });
    }

    public Student getSelectedStuInfo() {
        int selectedRow = mainViewTable.getSelectedRow();
        Student student =new Student();
        if (selectedRow >= 0) {
            student.setStuId((Integer) mainViewTable.getValueAt(selectedRow, 0));
            student.setStuName(mainViewTable.getValueAt(selectedRow, 1).toString());
            student.setStuGender(mainViewTable.getValueAt(selectedRow, 2).toString().charAt(0));
        }
        return student;
    }
    public void deleteStu(Integer stuId){
        Map<String, String> params = new HashMap<>();
        params.put("stuId",stuId.toString());
        PostUtils.postWithParams("http://localhost:8080/stu/deleteStu", params, new SuccessListener() {
            @Override
            public void success(String result) {
                ResultVO resultVO = JsonUtils.parseResult(result);
                System.out.println(result);
            }
        }, new FailListener() {
            @Override
            public void fail() {
                System.out.println("fail");
            }
        });
    }
}
