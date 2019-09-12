package com.zybar.bar.dao;

import com.zybar.bar.model.CloseStrategy;
import org.springframework.stereotype.Repository;

@Repository
public interface CloseStrategyMapper {

    int deleteByPrimaryKey(Integer closeStrategyId);


    int insert(CloseStrategy record);


    int insertSelective(CloseStrategy record);


    CloseStrategy selectByPrimaryKey(Integer closeStrategyId);


    int updateByPrimaryKeySelective(CloseStrategy record);


    int updateByPrimaryKey(CloseStrategy record);
}