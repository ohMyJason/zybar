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




    @PostMapping("/testSetZsetRedis")
    public Result testRedis(String key, String member, Double count) {
        redisUtil.addZSet(key,count,member);
        return Result.createSuccessResult();
    }

    @PostMapping("/testGetZsetRedis")
    public Result testGetRedis(String key) {
        System.out.println(redisUtil.getZsetRange(key));
        return Result.createSuccessResult();
    }

    @PostMapping("/testIncrZsetRedis")
    public  Result testIncrRedis(){
        System.out.println(redisUtil.updateZet("circle-range",1,"搞笑圈"));
        return Result.createSuccessResult();
    }

    @PostMapping("/testHaveKey")
    public  Result testHashKey(String member){
        if (redisUtil.hasMember("circle-range",member)){
            return Result.createSuccessResult("有");
        }else {
            return Result.createSuccessResult("没有");
        }
    }


    @PostMapping("/testListAdd")
    public Result testList(String key,String msg){
        redisUtil.lSet(key,msg);
        return Result.createSuccessResult();
    }

    @PostMapping("/testListGet")
    public Result testListGet(String key){
        List<Object> testMessage = redisUtil.lGet(key, 0, -1);
        return Result.createSuccessResult(testMessage);
    }
    @PostMapping("/testListLeftDelete")
    public  Result testListPush(String key){
        Object testMessage = redisUtil.leftRemove(key);
        return Result.createSuccessResult(testMessage);
    }

}
