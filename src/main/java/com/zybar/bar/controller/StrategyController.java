package com.zybar.bar.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zybar.bar.annotations.UserLoginToken;
import com.zybar.bar.dao.CloseStrategyMapper;
import com.zybar.bar.dao.ProductMapper;
import com.zybar.bar.dao.StrategyMapper;
import com.zybar.bar.dao.UserMapper;
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
import java.util.ArrayList;
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

    @Autowired
    UserMapper userMapper;

    @PostMapping("/insertProduct")
    public  Result insertProduct(Product product){
        productMapper.insertSelective(product);
        return Result.createSuccessResult();
    }

    @PostMapping("/getProduct")
    public Result getProduct(){
        List<Product> products = productMapper.selectAll();
        return Result.createSuccessResult(products.size(),products);
    }


    @PostMapping("/creatStrategy")
    public Result creatStrategy(@RequestBody Strategy strategy,HttpServletRequest httpServletRequest){
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
    public Result creatCloseStrategy(CloseStrategy closeStrategy){
        try {
            Strategy strategy = strategyMapper.selectByPrimaryKey(closeStrategy.getCreateStrategyId());

            //设置成已平仓
            if (strategy.getIsClose()==1){
                return Result.createByFailure("该策略已平仓");
            }
            strategy.setIsClose(1);
            //插入的时候主键回调
            closeStrategy.setCloseTime(fileUtil.getCurrTime());
            closeStrategyMapper.insertSelective(closeStrategy);
            //将平仓与建仓关联起来
            strategy.setCloseId(closeStrategy.getCloseStrategyId());
            //数据库更新建仓
            strategyMapper.updateByPrimaryKeySelective(strategy);
            return Result.createSuccessResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.createByFailure("异常,联系管理员");
        }

    }

    /**
     * 查询用户的建仓列表
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/getStrategyByToken")
    public Result getStrategy(HttpServletRequest httpServletRequest,int page,int limit){
        String userId = tokenService.getUserId(httpServletRequest);
        int start = PageCheck.calculateStart(page, limit);
        //这里不用分辨是否平仓，所以用null代替
        Integer ifClose = null;
        int count = strategyMapper.getCount(Integer.parseInt(userId),ifClose);
        List<Strategy> strategies = strategyMapper.selectStrategyByCreaterId(Integer.parseInt(userId),start,limit,ifClose);
        JSONArray res = new JSONArray();
//        将已经平仓的返回json对象里面加个平仓的json对象
        for (Strategy strategy : strategies){
            if (strategy.getIsClose() == 1) {
                JSONObject jsonStrategy = (JSONObject)JSON.toJSON(strategy);
                jsonStrategy.put("strategyId",strategy.getStrategyId());
                jsonStrategy.put("productName",productMapper.selectById(strategy.getProductId()).getProductName());
                jsonStrategy.put("teacherName",userMapper.selectByPrimaryKey(strategy.getCreaterId()).getUsername());
                CloseStrategy closeStrategy = closeStrategyMapper.selectByPrimaryKey(strategy.getCloseId());
                JSONObject jsonCloseStrategy = (JSONObject)JSON.toJSON(closeStrategy);
                jsonStrategy.put("closeStrategy",jsonCloseStrategy);
                res.add(jsonStrategy);
            }else {
                JSONObject jsonStrategy = (JSONObject)JSON.toJSON(strategy);
                jsonStrategy.put("strategyId",strategy.getStrategyId());
                jsonStrategy.put("productName",productMapper.selectById(strategy.getProductId()).getProductName());
                jsonStrategy.put("teacherName",userMapper.selectByPrimaryKey(strategy.getCreaterId()).getUsername());
                res.add(jsonStrategy);
            }
        }
        return  Result.createSuccessResult(count,res);
    }


    /**
     * 查询自己的的平仓列表
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/getCloseStrategy")
    public  Result getCloseStrategy(int page,int limit,HttpServletRequest httpServletRequest){
        String userId = tokenService.getUserId(httpServletRequest);
        int start = PageCheck.calculateStart(page, limit);
        List<HashMap<String, String>> closeStrages = strategyMapper.selectCloseStrategyByCreaterId(Integer.parseInt(userId), start, limit);
        return Result.createSuccessResult(closeStrages);
//        //查询已经平仓的建仓策略
//        Integer ifClose = 1;
//        int count = strategyMapper.getCount(Integer.parseInt(userId),ifClose);
//        List<Strategy> strategies = strategyMapper.selectStrategyByCreaterId(Integer.parseInt(userId),start,limit,ifClose);
//        ArrayList<CloseStrategy> closeStrategies = new ArrayList<>();
////        再根据建仓查对应平仓
//        for (Strategy strategy:strategies){
//            if (strategy.getIsClose()==1){
//                closeStrategies.add(closeStrategyMapper.selectByPrimaryKey(strategy.getCloseId()));
//            }
//        }
//
//        return Result.createSuccessResult(count,closeStrategies);
    }

    @PostMapping("/getStrategyItemByToken")
    public  Result getStrategyItemByToken(Integer strategyId){
        Strategy strategy = strategyMapper.selectByPrimaryKey(strategyId);
        return Result.createSuccessResult(strategy);
    }



}
