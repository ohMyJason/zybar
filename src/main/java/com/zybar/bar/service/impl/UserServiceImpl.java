package com.zybar.bar.service.impl;

import com.zybar.bar.dao.UserMapper;
import com.zybar.bar.model.User;
import com.zybar.bar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 刘佳昇
 * @Date 2019/8/8 10:21
 */

public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }
}
