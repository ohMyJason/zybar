package com.zybar.bar.service;


import com.zybar.bar.model.User;

/**
 * @author 刘佳昇
 * @Date 2019/8/14 15:23
 */

public interface TokenService {
    String getToken(User user);
}
