package com.zybar.bar.dao;

import com.zybar.bar.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 刘佳昇
 * @Date 2019/7/26 10:57
 */

@Mapper
public interface UserMapper {



    int insert(User user);
//    List<User> selectAllUser();
//    User getUserById(@Param(value="id") Integer id);

}
