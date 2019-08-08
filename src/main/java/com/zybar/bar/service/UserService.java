package com.zybar.bar.service;

import com.zybar.bar.dao.UserMapper;
import com.zybar.bar.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 刘佳昇
 * @Date 2019/7/26 14:16
 */

@Service
public interface UserService {

    int insertUser(User user);
}
