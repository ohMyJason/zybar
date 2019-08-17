package com.zybar.bar.controller;

import com.alibaba.fastjson.JSONObject;
import com.zybar.bar.model.App;
import com.zybar.bar.service.AppService;
import com.zybar.bar.util.FileUtil;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘佳昇
 * @Date 2019/8/8 19:54
 * 手机app
 */

@RestController
public class AppController {

    @Autowired
    AppService appService;

    @Autowired
    FileUtil fileUtil;


    /**
     * 插入App
     *
     * @param imgFile
     * @param app
     * @return
     */
    @PostMapping("/insertApp")
    public Result insertApp(@RequestParam("imgFile") MultipartFile imgFile, App app) {
        return appService.insertApp(imgFile, app);
    }

    /**
     * 获取全部app
     *
     * @return
     */
    @PostMapping("/getAllApp")
    public Result getAllApp() {
        return appService.selectApp();
    }


    /**
     * 删除app
     *
     * @param app
     * @return
     */
    @PostMapping("/deleteApp")
    public Result deleteApp(App app) {
        try{
            return appService.deleteApp(app.getAppId());
        }catch (Exception e){
            return Result.createByFailure("出错了");
        }
    }


    @PostMapping("/testList")
    public StringBuilder testList(@RequestBody JSONObject[] jsons) {
        System.out.println(jsons);
        System.out.println(jsons[0].get("password"));
        StringBuilder jsonToString = new StringBuilder();
        for (JSONObject json : jsons) {
            jsonToString.append(json.toJSONString());
        }
        return jsonToString;
    }


}
