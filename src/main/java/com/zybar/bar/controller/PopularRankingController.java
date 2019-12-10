package com.zybar.bar.controller;

import com.zybar.bar.dao.PopularRankingMapper;
import com.zybar.bar.model.PopularRanking;
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
 * @Date 2019/8/17 15:06
 */
@RestController
public class PopularRankingController {
    @Autowired
    PopularRankingMapper popularRankingMapper;


    @PostMapping("/insertPopularRanking")
    public Result insertPopularRanking(PopularRanking popularRanking){
        try {
            popularRankingMapper.insertSelective(popularRanking);
            return Result.createSuccessResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure("插入失败，请重试");
        }
    }


    @PostMapping("/getAllPopularRanking")
    public  Result getAllPopularRanking(@RequestBody HashMap<String,Integer> map){
        try {
            int page = map.get("page");
            int limit = map.get("limit");
            page = PageCheck.checkPage(page);
            limit = PageCheck.checkLimit(limit);
            int start = PageCheck.calculateStart(page,limit);
            int count = popularRankingMapper.getCount();
            List<PopularRanking> popularRankings = popularRankingMapper.selectAllPopularRanking(start,limit);
            if (popularRankings.size()>0){
                return Result.createSuccessResult(count,popularRankings);
            }else{
                return Result.createByFailure("没数据");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure("数据错误002，请联系管理员");
        }
    }

    @PostMapping("/deletePopularRanking")
    public Result deletePopularRanking(@RequestBody   HashMap<String,Integer> parm){
        try {
            int popularRankingId = parm.get("popularRankingId");
            int col = popularRankingMapper.deleteByPrimaryKey(popularRankingId);
            if (col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("未删除");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.createByFailure(e.getMessage());
        }

    }

    @PostMapping("/updatePopularRanking")
    public Result updatePopularRanking(@RequestBody  PopularRanking popularRanking){
        try {

            int col = popularRankingMapper.updateByPrimaryKeySelective(popularRanking);
            if (col>0){
                return Result.createSuccessResult();
            }else
                return Result.createByFailure("未更新");
        }catch (Exception e){
            e.printStackTrace();
            return Result.createByFailure(e.getMessage());
        }
    }



}
