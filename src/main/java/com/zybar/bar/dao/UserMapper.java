package com.zybar.bar.dao;

import com.zybar.bar.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbggenerated Sat Aug 17 13:57:43 CST 2019
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbggenerated Sat Aug 17 13:57:43 CST 2019
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbggenerated Sat Aug 17 13:57:43 CST 2019
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbggenerated Sat Aug 17 13:57:43 CST 2019
     */
    User selectByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbggenerated Sat Aug 17 13:57:43 CST 2019
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbggenerated Sat Aug 17 13:57:43 CST 2019
     */
    int updateByPrimaryKey(User record);



    User selectByUserName(User user);

    List<User> getAllUser(@Param("start") int start,@Param("limit") int limit,@Param("username") String username);

    int getCount(@Param("username") String username);
}