package com.zybar.bar.controller;

import com.zybar.bar.model.User;
import com.zybar.bar.service.UserService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 刘佳昇
 * @Date 2019/7/26 14:23
 */


@Controller
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping("/insert")
    public String InsertUser(User user){
        Integer col =userService.insertUser(user);

        System.out.println(col);

        return null;

    }

}
