package com.zybar.bar.controller;

import com.alibaba.fastjson.JSONObject;
import com.zybar.bar.util.FileUtil;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘佳昇
 * @Date 2019/8/25 4:39
 */
@RestController
public class ChatController {

    @Autowired
    FileUtil fileUtil;


    /**
     * 专为wangedit设计
     * @param file
     * @return
     */
    @PostMapping("/uploadChatImg")
    public JSONObject uploadChatImg(@RequestParam(name = "file")MultipartFile file){
        try {

            String url = fileUtil.fileUpload(file, 4);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("errno",0);
            jsonObject.put("data",new String[]{url});
            return jsonObject;
        }catch (Exception e){
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("errno",-1);
            return error;
        }
    }

}
