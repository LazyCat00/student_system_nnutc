package com.nnutc.view.ext;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class MainViewTable extends JTable {
    public MainViewTable() {
        //        设置表头
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(new Font(null, Font.BOLD, 16));
        tableHeader.setForeground(Color.RED);
//        设置表格体
        setFont(new Font(null, Font.PLAIN, 14));
//        字体颜色
        setForeground(Color.BLACK);
//        表格线
        setGridColor(Color.BLACK);
//        行高
        setRowHeight(30);
//        设置多行选择
        getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    public void renderRuler(){
        //        设置表格列的渲染方式
        Vector<String> colums = MainViewTableModel.getColums();//获取列名
        MainViewCellRender renderer = new MainViewCellRender();
        for (int i = 0; i < colums.size(); i++) {
            TableColumn column = getColumn(colums.get(i));
            column.setCellRenderer(renderer);
            if (i == 0) {
//                第一列宽度定死
                column.setPreferredWidth(50);
                column.setMaxWidth(50);
                column.setResizable(false);
            }
        }
    }
}
