package com.zybar.bar.controller;

import com.zybar.bar.dao.ZyvoiceMapper;
import com.zybar.bar.model.Zyvoice;
import com.zybar.bar.util.FileUtil;
import com.zybar.bar.util.PageCheck;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘佳昇
 * @Date 2019/8/23 17:24
 */
@RestController
public class ZyVoiceController {
    @Autowired
    ZyvoiceMapper zyvoiceMapper;

    @Autowired
    FileUtil fileUtil;

    @PostMapping("/getZyVoice")
    public Result getZyVoice(String mp3Name,int page,int limit){
       page= PageCheck.checkPage(page);
       limit = PageCheck.checkLimit(limit);
       int start = PageCheck.calculateStart(page, limit);
       return Result.createSuccessResult(zyvoiceMapper.getCount(mp3Name),zyvoiceMapper.getAllZyVoice(mp3Name,start,limit));
    }



    @PostMapping("/uploadZyVoice")
    public Result uploadZyVoice(@RequestParam(name = "file")MultipartFile file){
        String url = fileUtil.fileUpload(file, 3);
        return Result.createSuccessResult(url);
    }

    @PostMapping("/insertZyVoice")
    public Result insertZyVoice(Zyvoice zyvoice){
        try {

            zyvoiceMapper.insertSelective(zyvoice);
            return Result.createSuccessResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure("异常");
        }

    }

    @PostMapping("/deleteZyVoice")
    public Result deleteZyVoice(Integer voiceId){
        try {

            zyvoiceMapper.deleteByPrimaryKey(voiceId);
            return Result.createSuccessResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure("异常");
        }
    }



}
