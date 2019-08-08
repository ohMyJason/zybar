package com.zybar.bar.service.impl;

import com.zybar.bar.dao.AppMapper;
import com.zybar.bar.model.App;
import com.zybar.bar.service.AppService;
import com.zybar.bar.util.FileUtil;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

import static sun.plugin2.os.windows.OSVERSIONINFOA.size;

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
     * @param imgFile 图片
     * @param app
     * @return
     */
    @Override
    public Result insertApp(MultipartFile imgFile, App app) {
        String url = fileUtil.fileUpload(imgFile, 2);
        app.setPhotoUrl(url);
        if (!url.equals("-1")&&!url.equals("-2")&&!url.equals("-3")){
            int col = appMapper.insertSelective(app);
            if(col>0) {
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("添加App失败，请联系管理员");
            }
        }else {
            return Result.createByFailure("图片上传错误，请联系管理员");
        }

    }

    /**
     * 获取全部app
     * @return
     */
    @Override
    public Result selectApp() {
        List<App> apps = appMapper.selectAllApp();
        if (apps.size()>0){
            return Result.createSuccessResult(apps);
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
        if (appMapper.deleteByPrimaryKey(id)>0){
            return Result.createSuccessResult();
        }else {
            return Result.createByFailure("删除App失败，请联系管理员");
        }
    }
}
