package com.zybar.bar.controller;

import com.zybar.bar.dao.BigDataMapper;
import com.zybar.bar.model.BigData;
import com.zybar.bar.util.FileUtil;
import com.zybar.bar.util.PageCheck;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 刘佳昇
 * @Date 2019/8/27 3:11
 */
@RestController
public class BigDataController {
    @Autowired
    BigDataMapper bigDataMapper;
    @Autowired
    FileUtil fileUtil;


    @PostMapping("/getAllBigData")
    public Result getAllBigData(String name,int page,int limit ){
        try {
            page = PageCheck.checkPage(page);
            limit = PageCheck.checkLimit(limit);
            int start = PageCheck.calculateStart(page, limit);
            int count = bigDataMapper.getCount(name);
            List<BigData> allPlan = bigDataMapper.getAllPlan(name, start, limit);
            return Result.createSuccessResult(count,allPlan);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure();
        }

    }


    @PostMapping("/insertBigData")
    public Result insertBigData(BigData bigData){
        try {
            bigData.setUpdateTime(fileUtil.getCurrTime());
            bigDataMapper.insertSelective(bigData);
            return Result.createSuccessResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure();
        }
    }

    @PostMapping("/deleteBigData")
    public Result deleteBigData(Integer bigDataId){
        try {
            int col = bigDataMapper.deleteByPrimaryKey(bigDataId);
            if (col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure("异常");
        }
    }



}
