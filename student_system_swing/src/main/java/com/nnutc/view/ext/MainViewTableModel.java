package com.nnutc.view.ext;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MainViewTableModel extends DefaultTableModel {
    private MainViewTableModel() {
        super(null, colums);
    }
    static Vector<String> colums = new Vector<>();

    static {
        colums.add("学号");
        colums.add("姓名");
        colums.add("性别");
    }

//    单例
    private static MainViewTableModel mainViewTableModel = new MainViewTableModel();

    public static MainViewTableModel assembleModel(Vector<Vector<Object>> data) {
        mainViewTableModel.setDataVector(data, colums);
        return mainViewTableModel;
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
