package com.nnutc;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class JtableTest extends JFrame {
    public JtableTest() {
        super("JtableTest");


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

//        tableModel和jTable关联后，只需要更新model就能把数据的变化反应到jTable中
        StudentTableModel studentTableModel = StudentTableModel.assembleModel(data);
        JTable jTable = new JTable(studentTableModel);

//        设置表头
        JTableHeader tableHeader = jTable.getTableHeader();
        tableHeader.setFont(new Font(null, Font.BOLD, 16));
        tableHeader.setForeground(Color.RED);
//        设置表格体
        jTable.setFont(new Font(null, Font.PLAIN, 14));
//        字体颜色
        jTable.setForeground(Color.BLACK);
//        表格线
        jTable.setGridColor(Color.BLACK);
//        行高
        jTable.setRowHeight(30);
//        设置多行选择
        jTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//        设置表格列的渲染方式
        Vector<String> colums = StudentTableModel.getColums();//获取列名
        StudentCellRenderer renderer = new StudentCellRenderer();
        for (int i = 0; i < colums.size(); i++) {
            TableColumn column = jTable.getColumn(colums.get(i));
            column.setCellRenderer(renderer);
            if (i == 0) {
//                第一列宽度定死
                column.setPreferredWidth(50);
                column.setMaxWidth(50);
                column.setResizable(false);
            }
        }

        Container contentPane = getContentPane();
//        jTable放在jPanel上，默认不展示列头，需要特殊设置
//        放在jScrollPane 默认是展示列头
        JScrollPane jScrollPane = new JScrollPane(jTable);
        contentPane.add(jScrollPane);

        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    public static void main(String[] args) {
        new JtableTest();
    }
}


class StudentCellRenderer extends DefaultTableCellRenderer {
    //    在每一行每一列显示之前都会调用
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        偶数行
        if (row % 2 == 0) {
            setBackground(Color.LIGHT_GRAY);//浅灰色
        } else {
            setBackground(Color.WHITE);
        }
        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);//指定对齐方式-->居中
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}

//饿汉式单例？？？
class StudentTableModel extends DefaultTableModel {

    static Vector<String> colums = new Vector<>();

    static {
        colums.add("编号");
        colums.add("姓名");
        colums.add("学号");
        colums.add("家乡");
        colums.add("语文");
        colums.add("数学");
        colums.add("英语");
        colums.add("总分");
    }

    private StudentTableModel() {
        super(null, colums);
    }

    private static StudentTableModel studentTableModel = new StudentTableModel();

    public static StudentTableModel assembleModel(Vector<Vector<Object>> data) {
        studentTableModel.setDataVector(data, colums);
        return studentTableModel;
    }

    //    单元格是否可编辑
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public static Vector<String> getColums() {
        return colums;
    }
}