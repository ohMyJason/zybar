package com.zybar.bar.dao;

import com.zybar.bar.model.Jianerhuo;

public interface JianerhuoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_jianerhuo
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int deleteByPrimaryKey(Integer jId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_jianerhuo
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int insert(Jianerhuo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_jianerhuo
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int insertSelective(Jianerhuo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_jianerhuo
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    Jianerhuo selectByPrimaryKey(Integer jId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_jianerhuo
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int updateByPrimaryKeySelective(Jianerhuo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_jianerhuo
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int updateByPrimaryKey(Jianerhuo record);
}