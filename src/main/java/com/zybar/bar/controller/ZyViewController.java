package com.zybar.bar.controller;

import com.zybar.bar.dao.ZyViewMapper;
import com.zybar.bar.model.ZyView;
import com.zybar.bar.util.FileUtil;
import com.zybar.bar.util.PageCheck;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 刘佳昇
 * @Date 2019/8/16 17:45
 */

@RestController
public class ZyViewController {

    @Autowired
    ZyViewMapper zyViewMapper;

    @Autowired
    FileUtil fileUtil;


    /**
     * 插入状元视角
     * @param zyView
     * @return
     */
    @PostMapping("/insertZyView")
    public Result insertZyView(ZyView zyView){
        try {
            zyView.setZyViewCreatTiome(fileUtil.getCurrTime());
            int col = zyViewMapper.insertSelective(zyView);
            if (col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure();
            }
        }catch (Exception e){
            return Result.createByFailure("上传错误002，请重试");
        }
    }

    /**
     * 获取所有状元视角
     * @return
     */
    @PostMapping("/getAllZyView")
    public Result getAllZyView(String zyViewName,int page,int limit){
        try {
            page = PageCheck.checkPage(page);
            limit = PageCheck.checkLimit(limit);
            int start = PageCheck.calculateStart(page, limit);
            int count = zyViewMapper.getCount(zyViewName);
            List<ZyView> allZyView = zyViewMapper.getAllZyView(zyViewName,start,limit);
            return Result.createSuccessResult(count,allZyView);
        }catch (Exception e){
            return Result.createByFailure("数据错误002，请重试");
        }
    }


    /**
     * 删除状元视角
     * @param zyView
     * @return
     */
    @PostMapping("/deleteZyView")
    public Result deleteZyView(ZyView zyView){
        try {
            int col = zyViewMapper.deleteByPrimaryKey(zyView.getZyViewId());
            if (col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("删除失败003，请重试");
            }

        }catch (Exception e){
            return  Result.createByFailure("删除失败002，没有这条记录");
        }
    }
}
