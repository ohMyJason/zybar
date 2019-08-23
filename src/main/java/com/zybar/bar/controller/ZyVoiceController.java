package com.zybar.bar.controller;

import com.zybar.bar.dao.ZyvoiceMapper;
import com.zybar.bar.util.PageCheck;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘佳昇
 * @Date 2019/8/23 17:24
 */
@RestController
public class ZyVoiceController {
    @Autowired
    ZyvoiceMapper zyvoiceMapper;


    @PostMapping("/getZyVoice")
    public Result getZyVoice(String mp3Name,int page,int limit){
       page= PageCheck.checkPage(page);
       limit = PageCheck.checkLimit(limit);
       int start = PageCheck.calculateStart(page, limit);
       return Result.createSuccessResult(zyvoiceMapper.getCount(mp3Name),zyvoiceMapper.getAllZyVoice(mp3Name,start,limit));
    }





}
