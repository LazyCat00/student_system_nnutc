package com.nnutc.component;

import com.nnutc.common.ResultVO;
import com.nnutc.utils.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class StudentManageComponent extends Box {
    final int WIDTH = 850;
    final int HEIGHT = 600;
    JFrame jFrame = null;
    private JTable jTable;
    private Vector<String> titles;
    private Vector<Vector> tableData;
    private DefaultTableModel tableModel;

    public StudentManageComponent(JFrame jFrame) {
//        垂直布局
        super(BoxLayout.Y_AXIS);
        this.jFrame = jFrame;
//        组装视图
        JPanel buttonJPanel = new JPanel();
        buttonJPanel.setBackground(new Color(203, 220, 217));
        buttonJPanel.setMaximumSize(new Dimension(WIDTH, 80));
        buttonJPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton addButton = new JButton("添加");
        JButton uppdateButton = new JButton("修改");
        JButton deleteButton = new JButton("删除");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStudentDialog(jFrame, "添加学生", true).setVisible(true);
            }
        });

        buttonJPanel.add(addButton);
        buttonJPanel.add(uppdateButton);
        buttonJPanel.add(deleteButton);


//        组装表格
        String[] ts = {"学号", "姓名", "性别"};
        titles = new Vector<>();
        for (String title : ts) {
            titles.add(title);
        }
        tableData = new Vector<>();
        tableModel = new DefaultTableModel(tableData, titles);
        jTable = new JTable(tableModel) {
            //            表格不可编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
//        设置只能选中一行
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.add(jTable);


        JScrollPane scrollPane = new JScrollPane(jTable);
        this.add(buttonJPanel);
        this.add(scrollPane);
        requestData();
    }

    public void requestData() {
        GetUtils.get("http://localhost:8080//stu/queryAll", new SuccessListener() {
            @Override
            public void success(String result) {
                ResultVO resultVO = JsonUtils.parseResult(result);
                Vector<Vector> vectors = ResultVOData2Vector.convertResultVOData2Vector(resultVO);
//                清空tableData的数据
                tableData.clear();
                for (Vector vector : vectors) {
                    tableData.add(vector);
                }
//                刷新表格
                tableModel.fireTableDataChanged();
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });
    }
}
