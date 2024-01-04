package com.nnutc.utils;


import javax.swing.*;
import java.awt.*;

//尺寸工具类
public class DimensionUtil {
    public static Rectangle getBounds() {


//        可以创建的最大窗口的大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        确定窗口在屏幕上的位置以及边框的大小
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(new JFrame().getGraphicsConfiguration());
        Rectangle rectangle = new Rectangle(screenInsets.left, screenInsets.top, screenSize.width, screenSize.height);
        return rectangle;
    }

    public static void fullScreenWindow(Frame frame) {
        // 获取默认的GraphicsEnvironment
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        // 获取默认的GraphicsDevice
        GraphicsDevice device = env.getDefaultScreenDevice();

        // 进入全屏模式
        device.setFullScreenWindow(frame);
    }
}
