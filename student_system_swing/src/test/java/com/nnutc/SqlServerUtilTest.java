package com.nnutc;

import com.nnutc.utils.SqlserverUtil;

import java.sql.Connection;

public class SqlServerUtilTest {
    public static void main(String[] args) {
        try {
            Connection connection = SqlserverUtil.getConnection();
            System.out.println("数据库连接成功");
            System.out.println("connection="+connection);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }
}
