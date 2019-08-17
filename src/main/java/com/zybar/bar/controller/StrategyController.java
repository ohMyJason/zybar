package com.zybar.bar.controller;

import com.zybar.bar.annotations.UserLoginToken;
import com.zybar.bar.dao.StrategyMapper;
import com.zybar.bar.model.Strategy;
import com.zybar.bar.service.TokenService;
import com.zybar.bar.util.FileUtil;
import com.zybar.bar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 刘佳昇
 * @Date 2019/8/17 14:30
 */


@RestController
public class StrategyController {
    @Autowired
    TokenService tokenService;

    @Autowired
    StrategyMapper strategyMapper;

    @Autowired
    FileUtil fileUtil;

    @UserLoginToken
    @PostMapping("/insertStrategy")
    public Result insertStrategy(Strategy strategy, HttpServletRequest httpServletRequest){
        String userId = tokenService.getUserId(httpServletRequest);
        strategy.setUserId(Integer.parseInt(userId));
        strategy.setUpdateTime(fileUtil.getCurrTime());
        try {
            strategyMapper.insertSelective(strategy);
            return Result.createSuccessResult();
        }catch (Exception e ){
            System.out.println(e.getMessage());
            return Result.createByFailure("插入失败002，联系管理员");
        }
    }

    @UserLoginToken
    @PostMapping("/deleteStrategy")
    public Result deleteStrategy(Strategy strategy){
        try {
            int col = strategyMapper.deleteByPrimaryKey(strategy.getsId());
            if(col>0){
                return Result.createSuccessResult();
            }else {
                return Result.createByFailure("删除失败，并无此策略");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure("删除失败002，请重试");
        }
    }

    @PostMapping("/getAllStrategy")
    public Result getAllStrategy(){
        try {
            List<Strategy> allStrategy = strategyMapper.getAllStrategy();
            if (allStrategy.size()>0){
                return Result.createSuccessResult(allStrategy.size(),allStrategy);
            }else {
                return Result.createSuccessResult(0,null);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure("获取数据错误，请重试");
        }
    }



}
