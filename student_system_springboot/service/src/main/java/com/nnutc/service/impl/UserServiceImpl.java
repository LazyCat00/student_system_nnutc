package com.nnutc.service.impl;


import com.nnutc.bean.User;
import com.nnutc.common.vo.ResStatus;
import com.nnutc.common.vo.ResultVO;
import com.nnutc.dao.UserMapper;
import com.nnutc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    //    用户登录
    @Override
    public ResultVO login(String username, String password) {

        User user = userMapper.findUserByName(username);
        if (user == null) {
            return new ResultVO(ResStatus.LOGIN_SUCCESS, "登录失败", null);
        } else {
            if (user.getUserPwd().equals(password)) {
                return new ResultVO(ResStatus.LOGIN_SUCCESS, "登录成功", user);
            } else {
                return new ResultVO(ResStatus.LOGIN_FAIL_NOT, "登录失败", null);
            }
        }

    }

    @Override
    public ResultVO register(User user) {
        //1.根据用户查询，这个用户是否已经被注册
        User userByName = userMapper.findUserByName(user.getUserName());
        //2.如果没有被注册则进行保存操作
        if (userByName == null) {
            int i = userMapper.insertUser(user);
            if (i == 1) {
                return new ResultVO(ResStatus.OK, "注册成功", user);
            } else {
                return new ResultVO(ResStatus.NO, "注册失败", user);
            }
        } else {
            return new ResultVO(ResStatus.NO, "用户名已存在", user);
        }

    }
}
