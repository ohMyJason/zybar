package com.zybar.bar.dao;

import com.zybar.bar.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper {
    
    int insert(Product record);

    
    int insertSelective(Product record);
}