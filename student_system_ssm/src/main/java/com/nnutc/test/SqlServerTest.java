package com.nnutc.test;

import com.nnutc.bean.User;
import com.nnutc.dao.UserDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SqlServerTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        UserDAO userDAO = (UserDAO) context.getBean("userDAO");
        System.out.println(userDAO);
        User admin = userDAO.findUserByName("admin");
        System.out.println(admin);
    }
}
