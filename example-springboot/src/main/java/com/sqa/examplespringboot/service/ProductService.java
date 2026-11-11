package com.sqa.examplespringboot.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sqa.examplespringboot.entity.Product;

import java.util.List;


public interface ProductService extends IService<Product> {
    List<Product> getList(String keyWords);

}
