package com.zybar.bar.dao;

import com.zybar.bar.model.App;

public interface AppMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_app
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int deleteByPrimaryKey(Integer appId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_app
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int insert(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_app
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int insertSelective(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_app
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    App selectByPrimaryKey(Integer appId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_app
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int updateByPrimaryKeySelective(App record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_app
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int updateByPrimaryKey(App record);
}