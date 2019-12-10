package com.zybar.bar.controller;

import com.alibaba.fastjson.JSONObject;
import com.zybar.bar.dao.PlanMapper;
import com.zybar.bar.model.Plan;
import com.zybar.bar.util.FileUtil;
import com.zybar.bar.util.PageCheck;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

/**
 * @author 刘佳昇
 * @Date 2019/8/27 0:01
 */


@RestController
public class PlanController {

    @Autowired
    PlanMapper planMapper;

    @Autowired
    FileUtil fileUtil;

    @PostMapping("/insertPlan")
    public Result insertPlan(@RequestBody  Plan plan){
        try {
            plan.setUpdateTime(fileUtil.getCurrTime());
            planMapper.insertSelective(plan);
            return Result.createSuccessResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure();
        }
    }

    @PostMapping("/getAllPlan")
    public Result getAllPlan(String name,int page,int limit){
        try {
            page = PageCheck.checkPage(page);
            limit = PageCheck.checkLimit(limit);
            int start = PageCheck.calculateStart(page, limit);
            int count = planMapper.getCount(name);
            List<Plan> allPlan = planMapper.getAllPlan(name, start, limit);
            return Result.createSuccessResult(count,allPlan);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure();
        }
    }

    @PostMapping("/userGetAllPlan")
    public Result getAllPlan(){
        try {
            int page = 1;
            int limit =1000;
            String name =null;
            page = PageCheck.checkPage(page);
            limit = PageCheck.checkLimit(limit);
            int start = PageCheck.calculateStart(page, limit);
            int count = planMapper.getCount(name);
            List<Plan> allPlan = planMapper.getAllPlan(name, start, limit);
            return Result.createSuccessResult(count,allPlan);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure();
        }
    }


    @PostMapping("/deletePlan")
    public Result deletePlan(@RequestBody HashMap<String,Integer> parm){
        try {
            int planId = parm.get("planId");
            planMapper.deleteByPrimaryKey(planId);
            return Result.createSuccessResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure();
        }
    }

    @PostMapping("/uploadCommentImg")
    public Result uploadChatImg(@RequestParam(name = "file") MultipartFile file){
        String url = fileUtil.fileUpload(file, 2);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("src",url);
        return Result.createSuccessResult(jsonObject);
    }



}
