package com.zybar.bar.controller;

import com.zybar.bar.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘佳昇
 * @Date 2019/10/29 2:22
 */
@RestController
public class MessageController {


    @PostMapping("/insertMessage")
    public Result insertMessage(String message){
        return null;
    }
}
