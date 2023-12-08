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
        ResultVO resultVO;
        User user = userMapper.findUserByName(username);
        if (user == null) {
            return resultVO = new ResultVO(ResStatus.LOGIN_SUCCESS, "登录失败", null);
        } else {
            if (user.getUserPwd().equals(password)){
                return resultVO = new ResultVO(ResStatus.LOGIN_SUCCESS, "登录成功", user);
            }else {
                return resultVO = new ResultVO(ResStatus.LOGIN_SUCCESS, "登录失败", null);
            }
        }

    }
}
