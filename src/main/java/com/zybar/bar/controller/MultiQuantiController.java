package com.zybar.bar.controller;

import com.zybar.bar.dao.MultiQuantiMapper;
import com.zybar.bar.model.MultiQuanti;
import com.zybar.bar.util.PageCheck;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author 刘佳昇
 * @Date 2019/8/24 21:19
 */
@RestController
public class MultiQuantiController {
    @Autowired
    MultiQuantiMapper multiQuantiMapper;



    @PostMapping("/insertMultiQuanti")
    public Result insertMultiQuanti(MultiQuanti multiQuanti){
        try {
            int col = multiQuantiMapper.insertSelective(multiQuanti);
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

    @PostMapping("/deleteMultiQuanti")
    public Result deleteMultiQuanti(@RequestBody HashMap<String,Integer> parm){
        try {
            int multiQuantiId = parm.get("multiQuantiId");
            int col = multiQuantiMapper.deleteByPrimaryKey(multiQuantiId);
            if(col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure("异常");
        }
    }

    @PostMapping("/getAllMultiQuanti")
    public Result getAllMultiQuanti(Integer category,int page,int limit){
        try {
            page = PageCheck.checkPage(page);
            limit = PageCheck.checkLimit(limit);
            int start =PageCheck.calculateStart(page,limit);
            int count = multiQuantiMapper.getCount();
            List<MultiQuanti> multiQuantis = multiQuantiMapper.selectAll(category,start, limit);
            return Result.createSuccessResult(count,multiQuantis);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure();
        }
    }


    @PostMapping("/userGetAllMultiQuanti")
    public Result getAllMultiQuanti(@RequestBody HashMap<String,Object> map){
        try {
            int category = Integer.parseInt((String)map.get("category"));
            int page =1 ;
            int limit =10000;
            page = PageCheck.checkPage(page);
            limit = PageCheck.checkLimit(limit);
            int start =PageCheck.calculateStart(page,limit);
            int count = multiQuantiMapper.getCount();
            List<MultiQuanti> multiQuantis = multiQuantiMapper.selectAll(category,start, limit);
            return Result.createSuccessResult(count,multiQuantis);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure();
        }
    }

}
