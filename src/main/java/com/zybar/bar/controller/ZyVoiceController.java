package com.zybar.bar.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zybar.bar.dao.UserMapper;
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

import java.util.List;

/**
 * @author 刘佳昇
 * @Date 2019/8/23 17:24
 */
@RestController
public class ZyVoiceController {
    @Autowired
    ZyvoiceMapper zyvoiceMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    FileUtil fileUtil;


    /**
     * @param mp3Name
     * @param page
     * @param limit
     * @param flag    判断是用户端发出的还是后台发出的，因为用户端需要特殊返回数据 1-用户  2-后台
     * @return
     */
    @PostMapping("/getZyVoice")
    public Result getZyVoice(String mp3Name, int page, int limit, Integer flag) {
        page = PageCheck.checkPage(page);
        limit = PageCheck.checkLimit(limit);
        int start = PageCheck.calculateStart(page, limit);
        List<Zyvoice> allZyVoice = zyvoiceMapper.getAllZyVoice(mp3Name, start, limit);
        int count = zyvoiceMapper.getCount(mp3Name);
        //特殊处理一波
        if (flag == 1) {
            JSONArray datas = new JSONArray();
            for (Zyvoice voice : allZyVoice) {
                JSONObject data = new JSONObject();
                data.put("src",voice.getMp3Url());
                data.put("name",voice.getMp3Name());
                data.put("author",userMapper.selectByPrimaryKey(voice.getUserId()).getUsername());
                data.put("cover",voice.getPhotoUrl());
                datas.add(data);
            }
            return Result.createSuccessResult(datas);
        } else {
            return Result.createSuccessResult(count, allZyVoice);
        }
    }


    @PostMapping("/uploadZyVoice")
    public Result uploadZyVoice(@RequestParam(name = "file") MultipartFile file) {
        String url = fileUtil.fileUpload(file, 3);
        return Result.createSuccessResult(url);
    }

    @PostMapping("/insertZyVoice")
    public Result insertZyVoice(Zyvoice zyvoice) {
        try {
            zyvoice.setPhotoUrl(userMapper.selectByPrimaryKey(zyvoice.getUserId()).getPhotoUrl());
            zyvoiceMapper.insertSelective(zyvoice);
            return Result.createSuccessResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Result.createByFailure("异常");
        }

    }

    @PostMapping("/deleteZyVoice")
    public Result deleteZyVoice(Integer voiceId) {
        try {

            zyvoiceMapper.deleteByPrimaryKey(voiceId);
            return Result.createSuccessResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Result.createByFailure("异常");
        }
    }


}
