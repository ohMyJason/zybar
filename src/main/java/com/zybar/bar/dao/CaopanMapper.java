package com.zybar.bar.dao;

import com.zybar.bar.model.Caopan;

public interface CaopanMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_caopan
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int deleteByPrimaryKey(Integer paperId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_caopan
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int insert(Caopan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_caopan
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int insertSelective(Caopan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_caopan
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    Caopan selectByPrimaryKey(Integer paperId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_caopan
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int updateByPrimaryKeySelective(Caopan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_caopan
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int updateByPrimaryKey(Caopan record);
}