package com.zybar.bar.controller;

import com.alibaba.fastjson.JSONObject;
import com.zybar.bar.dao.LiveMapper;
import com.zybar.bar.model.Live;
import com.zybar.bar.util.FileUtil;
import com.zybar.bar.util.PageCheck;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author 刘佳昇
 * @Date 2019/9/6 2:00
 */
@RestController
public class LiveController {
    @Autowired
    LiveMapper liveMapper;

    @Autowired
    FileUtil fileUtil;

    @Value("${img.path}")
    public String localPath;


    @PostMapping("/uploadLiveImg")
    public Result uploadLiveImg(@RequestParam(name = "file") MultipartFile file) {
        try {

            String imgUrl = fileUtil.fileUpload(file, 2);
            if (!imgUrl.equals("-1") && !imgUrl.equals("-2") && !imgUrl.equals("-3")) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("imgUrl", imgUrl);
                return Result.createSuccessResult(jsonObject);
            } else {
                return Result.createByFailure("上传失败");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Result.createByFailure("异常");
        }
    }

    @PostMapping("/insertLive")
    public Result insertLive(Live live) {
        try {
            String noSpaceUrl = live.getLiveUrl().replace(" ",""); //去除全部空格
            live.setLiveUrl(noSpaceUrl);
            liveMapper.insertSelective(live);
            return Result.createSuccessResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Result.createByFailure("异常");
        }
    }


    /**
     *
     * @ page
     * @ limit
     * @ liveName
     * @return
     */
    @PostMapping("/getAllLive")
    public Result getAllLive(@RequestBody HashMap<String,Object> map) {
        try {
            int page = (Integer)map.get("page");
            int limit = (Integer) map.get("limit");
            String liveName = map.containsKey("liveName")?(String)map.get("liveName"):null;
            page = PageCheck.checkPage(page);
            limit = PageCheck.checkLimit(limit);
            int start = PageCheck.calculateStart(page, limit);
            List<Live> allLive = liveMapper.getAllLive(start, limit, liveName);
            int count = liveMapper.getCount(liveName);
            if (count > 0)
                return Result.createSuccessResult(count, allLive);
            else
                return Result.createByFailure("无数据");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.createByFailure(e.getMessage());
        }
    }

    @PostMapping("/deleteLive")
    public Result deleteLive(@RequestBody HashMap<String,Integer> parm) {
        try {
            int liveId = parm.get("liveId");
            Live live = liveMapper.selectByPrimaryKey(liveId);
            String[] splits = live.getLiveImgUrl().split("/");//获得文件名
            String imgName = splits[splits.length - 1];
            int col = liveMapper.deleteByPrimaryKey(liveId);
            String res = fileUtil.deleteFile(localPath + imgName);
            if (res.equals("0")||res.equals("-2")) {
                if (col > 0) {
                    return Result.createSuccessResult();
                } else {
                    return Result.createByFailure("数据库删除失败");
                }
            } else {
                return Result.createByFailure("文件删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.createByFailure(e.getMessage());
        }
    }

    @PostMapping("/updateLive")
    public Result updateLive(Live live){
        try {
            Live baseLive = liveMapper.selectByPrimaryKey(live.getLiveId());  //为了获取原条目的图片url
            if (baseLive.getLiveImgUrl().equals(live.getLiveImgUrl())){  //图片没改的话
                int col = liveMapper.updateByPrimaryKeySelective(live);
                if (col>0){
                    return Result.createSuccessResult();
                }else {
                    return Result.createByFailure("数据库更新失败");
                }
            }else {
                String res = fileUtil.deleteFile(localPath + fileUtil.getFileName(baseLive.getLiveUrl()));
                if (res.equals("0")||res.equals("-2")){ //图片删除成功，或者图片根本就不存在
                    int col = liveMapper.updateByPrimaryKeySelective(live);
                    if (col>0){
                        return Result.createSuccessResult();
                    }else {
                        return Result.createByFailure("原图片删除成功，数据库更新失败");
                    }
                }else {
                    return Result.createByFailure("原图片删除失败");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure("异常");
        }

    }


}
