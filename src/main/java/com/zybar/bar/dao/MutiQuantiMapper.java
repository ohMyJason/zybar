package com.zybar.bar.dao;

import com.zybar.bar.model.MutiQuanti;

public interface MutiQuantiMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_muti_quanti
     *
     * @mbggenerated Thu Aug 08 10:12:12 CST 2019
     */
    int deleteByPrimaryKey(Integer mutiId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_muti_quanti
     *
     * @mbggenerated Thu Aug 08 10:12:12 CST 2019
     */
    int insert(MutiQuanti record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_muti_quanti
     *
     * @mbggenerated Thu Aug 08 10:12:12 CST 2019
     */
    int insertSelective(MutiQuanti record);
}