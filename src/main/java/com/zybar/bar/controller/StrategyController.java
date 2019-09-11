package com.zybar.bar.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zybar.bar.annotations.UserLoginToken;
import com.zybar.bar.dao.CloseStrategyMapper;
import com.zybar.bar.dao.ProductMapper;
import com.zybar.bar.dao.StrategyMapper;
import com.zybar.bar.model.CloseStrategy;
import com.zybar.bar.model.Product;
import com.zybar.bar.model.Strategy;
import com.zybar.bar.service.TokenService;
import com.zybar.bar.util.FileUtil;
import com.zybar.bar.util.PageCheck;
import com.zybar.bar.util.Result;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
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
    CloseStrategyMapper closeStrategyMapper;

    @Autowired
    FileUtil fileUtil;

    @Autowired
    ProductMapper productMapper;

    @PostMapping("/insertProduct")
    public  Result insertProduct(Product product){
        productMapper.insertSelective(product);
        return Result.createSuccessResult();
    }


    @PostMapping("/creatStrategy")
    public Result creatStrategy(Strategy strategy,HttpServletRequest httpServletRequest){
        try {
            String createrId = tokenService.getUserId(httpServletRequest);
            strategy.setCreaterId(Integer.parseInt(createrId));
            strategy.setCreatTime(fileUtil.getCurrTime());
            strategyMapper.insertSelective(strategy);
            return Result.createSuccessResult();
        }catch (Exception e ){
            System.out.println(e.getMessage());
            return Result.createByFailure("异常");
        }
    }


    @PostMapping("/creatCloseStrategy")
    @Transactional
    public Result creatCloseStrategy(Integer strategyId, CloseStrategy closeStrategy){
        try {
            Strategy strategy = strategyMapper.selectByPrimaryKey(strategyId);

            //设置成已平仓
            if (strategy.getIsClose()==1){
                return Result.createByFailure("该策略已平仓");
            }
            strategy.setIsClose(1);
            //插入的时候主键回调
            closeStrategy.setCloseTime(fileUtil.getCurrTime());
            closeStrategy.setCreatStrategyId(strategyId);
            closeStrategyMapper.insertSelective(closeStrategy);
            //将平仓与建仓关联起来
            strategy.setCloseId(closeStrategy.getCloseStrategyId());
            //数据库更新建仓
            strategyMapper.updateByPrimaryKeySelective(strategy);
            return Result.createSuccessResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure("异常");
        }

    }


    @PostMapping("/getStrategyByToken")
    public Result getStrategy(HttpServletRequest httpServletRequest){
        String userId = tokenService.getUserId(httpServletRequest);
        List<Strategy> strategies = strategyMapper.selectStrategyByCreaterId(Integer.parseInt(userId));
        JSONArray res = new JSONArray();
        for (Strategy strategy : strategies){
            if (strategy.getIsClose() == 1) {
                JSONObject jsonStrategy = (JSONObject)JSON.toJSON(strategy);
                CloseStrategy closeStrategy = closeStrategyMapper.selectByPrimaryKey(strategy.getCloseId());
                JSONObject jsonCloseStrategy = (JSONObject)JSON.toJSON(closeStrategy);
                jsonStrategy.put("closeStrategy",jsonCloseStrategy);
                res.add(jsonStrategy);
            }else {
                JSONObject jsonStrategy = (JSONObject)JSON.toJSON(strategy);
                res.add(jsonStrategy);
            }
        }
        return Result.createSuccessResult(res.size(),res);
    }


    //todo 还没写
    @PostMapping("/getCloseStrategy")
    public  Result getCloseStrategy(HttpServletRequest httpServletRequest){
        String userId = tokenService.getUserId(httpServletRequest);
        List<Strategy> strategies = strategyMapper.selectStrategyByCreaterId(Integer.parseInt(userId));
        return null;
    }


}
