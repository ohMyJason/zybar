package com.zybar.bar.dao;

import com.zybar.bar.model.Strategy;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface StrategyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_strategy
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int deleteByPrimaryKey(Integer sId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_strategy
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int insert(Strategy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_strategy
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int insertSelective(Strategy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_strategy
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    Strategy selectByPrimaryKey(Integer sId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_strategy
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int updateByPrimaryKeySelective(Strategy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_strategy
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int updateByPrimaryKey(Strategy record);
}