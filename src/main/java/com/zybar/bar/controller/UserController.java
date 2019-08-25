package com.zybar.bar.controller;


import com.auth0.jwt.JWT;
import com.zybar.bar.annotations.UserLoginToken;
import com.zybar.bar.model.User;
import com.zybar.bar.service.TokenService;
import com.zybar.bar.service.UserService;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘佳昇
 * @Date 2019/7/26 14:23
 */


@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    //登录 -> 检查用户名密码与数据库中的记录是否匹配
    @PostMapping("/login")
    public Result login(User user) {
//        User userForBase = userService.findByUsername(user);
//        上面是根据用户名登录，这里是根据id登录
        User userForBase = userService.findUserById(user.getUserId());
        if (userForBase == null) {
            return Result.createByFailure("登录失败,用户不存在");
        } else {
            if (!userForBase.getPassword().equals(user.getPassword())) {
                return Result.createByFailure("登录失败,密码错误");
            } else {
                String token = tokenService.getToken(userForBase);
                List<Object> listObject = new ArrayList<>();
                listObject.add(userForBase);
                listObject.add(token);
                return Result.createSuccessResult(2, listObject);
            }
        }
    }

    @UserLoginToken
    @PostMapping("/getMessage")
    public String getMessage() {
        try {
            return "你已通过验证";
        } catch (Exception e) {
            return "请登录";
        }
    }

    @UserLoginToken
    @PostMapping("/testGetUserId")
    public Result testGetUserId(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        String userId = JWT.decode(token).getAudience().get(0);
        User user = userService.findUserById(Integer.parseInt(userId));
        return Result.createSuccessResult(user);
    }


}
