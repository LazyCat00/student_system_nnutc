package com.nnutc.dao;

import com.nnutc.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml","classpath:spring-mvc.xml","classpath:spring-mybatis.xml"})
public class UserDAOTest {
@Resource
private UserDAO userDAO;
    @Test
    public void findUserByName() {
        User admin = userDAO.findUserByName("admin");
        System.out.println(admin);
    }
}