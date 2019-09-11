package com.zybar.bar.dao;

import com.zybar.bar.model.Strategy;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StrategyMapper {
    
    int deleteByPrimaryKey(Integer strategyId);

    
    int insert(Strategy record);

    
    int insertSelective(Strategy record);

    
    Strategy selectByPrimaryKey(Integer strategyId);

    
    int updateByPrimaryKeySelective(Strategy record);

    
    int updateByPrimaryKey(Strategy record);

    List<Strategy> selectStrategyByCreaterId(@Param("createrId") int createrId);
}