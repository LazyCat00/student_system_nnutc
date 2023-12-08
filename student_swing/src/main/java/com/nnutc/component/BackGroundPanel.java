package com.nnutc.component;

import javax.swing.*;
import java.awt.*;

public class BackGroundPanel extends JPanel {
    //    背景图片
    private Image backgroundImage;

    //    谁用这个背景panel，谁传进来一个背景图片
    public BackGroundPanel(Image image) {
        this.backgroundImage = image;
    }

    //    绘制背景
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //绘制背景
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
