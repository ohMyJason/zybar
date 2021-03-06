package com.zybar.bar.dao;

import com.zybar.bar.model.BigData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BigDataMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_big_data
     *
     * @mbggenerated Tue Aug 27 03:11:10 CST 2019
     */
    int deleteByPrimaryKey(Integer bigDataId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_big_data
     *
     * @mbggenerated Tue Aug 27 03:11:10 CST 2019
     */
    int insert(BigData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_big_data
     *
     * @mbggenerated Tue Aug 27 03:11:10 CST 2019
     */
    int insertSelective(BigData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_big_data
     *
     * @mbggenerated Tue Aug 27 03:11:10 CST 2019
     */
    BigData selectByPrimaryKey(Integer bigDataId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_big_data
     *
     * @mbggenerated Tue Aug 27 03:11:10 CST 2019
     */
    int updateByPrimaryKeySelective(BigData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_big_data
     *
     * @mbggenerated Tue Aug 27 03:11:10 CST 2019
     */
    int updateByPrimaryKey(BigData record);

    int getCount(@Param("name") String name);

    List<BigData> getAllPlan(@Param("name") String name,@Param("start") int start,@Param("limit") int limit);
}