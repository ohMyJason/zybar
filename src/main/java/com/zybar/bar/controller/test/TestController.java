package com.zybar.bar.controller.test;


import com.zybar.bar.util.RedisUtil;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 刘佳昇
 * @Date 2019/9/10 11:24
 */

@RestController
@RequestMapping("/test")
public class TestController {




    @Autowired
    RedisUtil redisUtil;




    @PostMapping("/testSetRedis")
    public Result testRedis(String key, String member, Double count) {
        redisUtil.addZSet(key,count,member);
        return Result.createSuccessResult();
    }

    @PostMapping("/testGetRedis")
    public Result testGetRedis(String key) {
        System.out.println(redisUtil.getZsetRange(key));
        return Result.createSuccessResult();
    }

    @PostMapping("/testIncrRedis")
    public  Result testIncrRedis(){
        System.out.println(redisUtil.updateZet("circle-range",1,"搞笑圈"));
        return Result.createSuccessResult();
    }

    @PostMapping("/testHashKey")
    public  Result testHashKey(String member){
        if (redisUtil.hasMember("circle-range",member)){
            return Result.createSuccessResult("有");
        }else {
            return Result.createSuccessResult("没有");
        }
    }




}
