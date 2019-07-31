package com.zybar.bar.controller;

import com.zybar.bar.BarApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 刘佳昇
 * @Date 2019/7/26 14:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BarApplication.class)
public class UserControllerTest {


    @Autowired
    UserController userController;



}
