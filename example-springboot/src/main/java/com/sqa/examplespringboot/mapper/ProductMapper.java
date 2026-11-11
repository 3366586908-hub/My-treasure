package com.sqa.examplespringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqa.examplespringboot.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
