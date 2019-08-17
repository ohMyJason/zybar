package com.zybar.bar.controller;

import com.zybar.bar.dao.WinningRateRankingMapper;
import com.zybar.bar.model.WinningRateRanking;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result getAllWinningRateRanking(){
        try {
            List<WinningRateRanking> allWinningRateRanking = winningRateRankingMapper.getAllWinningRateRanking();
            if (allWinningRateRanking.size()>0){
                return Result.createSuccessResult(allWinningRateRanking.size(),allWinningRateRanking);
            }else {
                return Result.createSuccessResult(0,null);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure("获取数据错误003，请联系管理员");
        }
    }


    @PostMapping("/deleteWinRete")
    public Result deleteWinRete(WinningRateRanking winningRateRanking){
        try {
            int col = winningRateRankingMapper.deleteByPrimaryKey(winningRateRanking.getWinningRateRankingId());
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
