package com.nnutc.view.ext;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MainViewCellRender extends DefaultTableCellRenderer {
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
