package com.zybar.bar.dao;

import com.zybar.bar.model.CloseStrategy;
import com.zybar.bar.model.Strategy;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@Repository
public interface StrategyMapper {
    
    int deleteByPrimaryKey(Integer strategyId);

    
    int insert(Strategy record);

    
    int insertSelective(Strategy record);

    
    Strategy selectByPrimaryKey(Integer strategyId);

    
    int updateByPrimaryKeySelective(Strategy record);

    
    int updateByPrimaryKey(Strategy record);

    List<Strategy> selectStrategyByCreaterId(@Param("createrId") Integer createrId,@Param("start") int start,@Param("limit") int limit,@Param("ifClose") Integer ifClose,@Param("selectLive") Integer selectLive);

    int getCount(@Param("createrId") Integer createrId,@Param("ifClose") Integer ifClose,@Param("selectLive") Integer selectLive);

    List<HashMap<String,String>> selectCloseStrategyByCreaterId(@Param("createrId") Integer createrId, @Param("start") int start, @Param("limit") int limit,@Param("selectLive") int selectLive );
}