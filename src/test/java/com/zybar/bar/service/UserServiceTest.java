package com.zybar.bar.service;

import com.zybar.bar.BarApplication;
import com.zybar.bar.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 刘佳昇
 * @Date 2019/7/26 14:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BarApplication.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testInsert(){
        User user = new User();
        user.setUserId(1);
        System.out.println(userService.insertUser(user));

    }

}
