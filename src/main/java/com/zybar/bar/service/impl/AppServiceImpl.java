package com.zybar.bar.service.impl;

import com.zybar.bar.dao.AppMapper;
import com.zybar.bar.model.App;
import com.zybar.bar.service.AppService;
import com.zybar.bar.util.FileUtil;
import com.zybar.bar.util.PageCheck;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 刘佳昇
 * @Date 2019/8/8 20:06
 */

@Service
public class AppServiceImpl implements AppService {
    @Autowired
    AppMapper appMapper;

    @Autowired
    FileUtil fileUtil;

    /**
     * 插入App

     * @param app
     * @return
     */
    @Override
    public Result insertApp( App app) {

        int col = appMapper.insertSelective(app);
        if(col>0) {
            return Result.createSuccessResult();
        }else {
            return Result.createByFailure("添加App失败，请联系管理员");
        }

    }

    /**
     * 获取全部app
     * @return
     * @param name
     * @param page
     * @param limit
     */
    @Override
    public Result selectApp(String name, int page, int limit) {
        page = PageCheck.checkPage(page);
        limit = PageCheck.checkLimit(limit);
        int start = PageCheck.calculateStart(page, limit);
        int count = appMapper.getCount(name);
        List<App> apps = appMapper.selectAllApp(name,start,limit);
        if (apps.size()>0){
            return Result.createSuccessResult(count,apps);
        }else{
            return Result.createByFailure("获取App失败，请联系管理员");
        }
    }

    /**
     * 删除app
     * @param id
     * @return
     */
    @Override
    public Result deleteApp(Integer id) {
        try {

            App app = appMapper.selectByPrimaryKey(id);
            fileUtil.deleteFile(app.getPhotoUrl());
            if (appMapper.deleteByPrimaryKey(id)>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("删除App失败，请联系管理员");
            }
        }catch (Exception e){
                return Result.createByFailure("出错");
        }
    }
}
