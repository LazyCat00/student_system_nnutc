package com.nnutc.service.impl;

import com.nnutc.bean.Admin;
import com.nnutc.service.AdminService;
import com.nnutc.utils.SqlserverUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
//   输入用户名为 1'or
//   输入密码为   or '1'='1
public class AdminServiceImpl implements AdminService {
    @Override
    public boolean validateAdmin(Admin admin) {
        String username = admin.getUsername();
        String sql = "select pwd from manager where user_name = '" + username + "'";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = SqlserverUtil.getConnection();
            if (connection == null) {
                return false;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String password = resultSet.getString(1);
                if (admin.getPassword().equals(password)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlserverUtil.close(resultSet, statement, connection);
        }
        return false;
    }
}
