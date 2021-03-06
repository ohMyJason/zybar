package com.zybar.bar.controller;

import com.zybar.bar.util.RedisUtil;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘佳昇
 * @Date 2019/10/29 2:22
 */
@RestController
public class MessageController {


    @Autowired
    RedisUtil redisUtil;

    @PostMapping("/insertMessage")
    public Result insertMessage(String message, String selectOnline) {
        try {
            String key = "messageList" + selectOnline;
            redisUtil.lSet(key, message);
            redisUtil.expire(key, 85400);
            if (900 < redisUtil.lGetListSize(key)) {  //缓存900条消息，超过就删最左边的
                redisUtil.leftRemove(key);
            }
            return Result.createSuccessResult();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.createByFailure(e.getMessage());
        }
    }

    @PostMapping("/getMessageList")
    public Result getMessage(String selectOnline) {
        try {
            return Result.createSuccessResult(redisUtil.lGet("messageLise" + selectOnline, 0, -1));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.createByFailure(e.getMessage());
        }
    }
}
