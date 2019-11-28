package com.zybar.bar.controller;

import com.zybar.bar.dao.WinningRateRankingMapper;
import com.zybar.bar.model.WinningRateRanking;
import com.zybar.bar.util.PageCheck;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author 刘佳昇
 * @Date 2019/8/17 15:34
 */

@RestController
public class WinningRateRankingController {
    @Autowired
    WinningRateRankingMapper winningRateRankingMapper;

    @PostMapping("/insertWinRete")
    public Result insertWinningRateRanking(WinningRateRanking winningRateRanking){
        try {

            int col = winningRateRankingMapper.insertSelective(winningRateRanking);
            if (col>0){
                return Result.createSuccessResult();
            }else{
                return Result.createByFailure("插入失败001，请重试");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure("插入失败003，请联系管理员");
        }
    }

    @PostMapping("/getAllWinRete")
    public Result getAllWinningRateRanking(@RequestBody HashMap<String,Integer> map){
        try {
            int page = map.get("page");
            int limit = map.get("limit");
            page = PageCheck.checkPage(page);
            limit = PageCheck.checkLimit(limit);
            int start = PageCheck.calculateStart(page, limit);
            int count = winningRateRankingMapper.getCount();
            List<WinningRateRanking> allWinningRateRanking = winningRateRankingMapper.getAllWinningRateRanking(start,limit);
            if (allWinningRateRanking.size()>0){
                return Result.createSuccessResult(count,allWinningRateRanking);
            }else {
                return Result.createByFailure("没数据");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure("获取数据错误003，请联系管理员");
        }
    }


    @PostMapping("/deleteWinRete")
    public Result deleteWinRete(int winningRateRankingId){
        try {
            int col = winningRateRankingMapper.deleteByPrimaryKey(winningRateRankingId);
            if (col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("删除失败001，请重试");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure("删除失败003，请联系管理员");
        }

    }

    @PostMapping("updateWinRate")
    public Result updateWinRate(WinningRateRanking winningRateRanking){
        try {
            int col = winningRateRankingMapper.updateByPrimaryKeySelective(winningRateRanking);
            if (col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("更新失败001，请重试");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure("更新失败003,请联系管理员");
        }
    }

}
