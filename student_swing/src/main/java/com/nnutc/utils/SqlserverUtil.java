package com.nnutc.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class SqlserverUtil {
    //sql server 驱动name
    private static final String driver;
    //sql server 数据库ip，端口以及数据库名称
    private static final String url;
    //sql server 用户名
    private static final String user;
    //密码
    private static final String password;

    static {
        try {
            Properties properties = new Properties();
//            student_swing\src\main\resources\sqlserver.properties
            properties.load(new FileInputStream("student_swing\\src\\main\\resources\\sqlserver.properties"));
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //获取数据库连接
    public static Connection getConnection() {
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //关闭连接
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
