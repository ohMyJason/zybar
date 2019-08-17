package com.zybar.bar.service.impl;

import com.zybar.bar.dao.UserMapper;
import com.zybar.bar.model.User;
import com.zybar.bar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 刘佳昇
 * @Date 2019/8/8 10:21
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public int insertUser(User user) {
        return 0;
    }

    @Override
    public User findUserById(int parseInt) {
        try {
           return userMapper.selectByPrimaryKey(parseInt);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public User findByUsername(User user) {
        try{
            return userMapper.selectByUserName(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
