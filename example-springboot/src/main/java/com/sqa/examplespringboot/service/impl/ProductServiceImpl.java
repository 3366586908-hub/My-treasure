package com.sqa.examplespringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqa.examplespringboot.entity.Product;
import com.sqa.examplespringboot.mapper.ProductMapper;
import com.sqa.examplespringboot.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public List<Product> getList(String keyWords) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        if(keyWords!=null && !keyWords.isEmpty()){
            queryWrapper.like("name",keyWords);
        }
        return this.list(queryWrapper);
    }
}
