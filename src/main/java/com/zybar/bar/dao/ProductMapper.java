package com.zybar.bar.dao;

import com.zybar.bar.model.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
    
    int insert(Product record);

    
    int insertSelective(Product record);

    Product selectById(@Param("productId") Integer productId);

    List<Product> selectAll();

    List<Product> tableSelectAll(@Param("start") Integer start,@Param("limit") Integer limit,@Param("productName") String productName);
    int getCount(@Param("productName") String productName);

    int deleteById(@Param("productId") Integer productId);
}