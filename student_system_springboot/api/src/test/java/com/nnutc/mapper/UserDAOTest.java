package com.nnutc.mapper;
import com.nnutc.bean.User;
import com.nnutc.dao.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDAOTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void findUserByName(){
        User user = userMapper.findUserByName("admin");
        System.out.println(user);
    }

}
