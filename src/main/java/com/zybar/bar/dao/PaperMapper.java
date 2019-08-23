package com.zybar.bar.dao;

import com.zybar.bar.model.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaperMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int deleteByPrimaryKey(Integer paperId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int insert(Paper record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int insertSelective(Paper record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    Paper selectByPrimaryKey(Integer paperId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int updateByPrimaryKeySelective(Paper record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_paper
     *
     * @mbggenerated Thu Aug 08 17:28:24 CST 2019
     */
    int updateByPrimaryKey(Paper record);

    List<Paper> selectAllPaper(@Param("paperName") String paperName,@Param("start") int start,@Param("limit") int limit);

    int getCount(@Param("paperName") String paperName);

    int getCount();
}