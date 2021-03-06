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
     * @param app
     * @return
     */
    @PostMapping("/insertApp")
    public Result insertApp(App app) {
        return appService.insertApp(app);
    }

    @PostMapping("/uploadApp")
    public Result uploadApp(@RequestParam("file") MultipartFile file){
        try {
            String url = fileUtil.fileUpload(file, 2);

            if (!url.equals("-1")&&!url.equals("-2")&&!url.equals("-3")){
                return Result.createSuccessResult(url);
            }else {
                return Result.createByFailure("图片上传错误，请联系管理员");
            }
        }catch (Exception e){
            return Result.createByFailure("出错");
        }
    }

    /**
     * 获取全部app
     *
     * @return
     */
    @PostMapping("/getAllApp")
    public Result getAllApp(String name,int page,int limit) {
        return appService.selectApp(name,page,limit);
    }

    @PostMapping("/userGetAllApp")
    public Result userGetAllApp(){
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
