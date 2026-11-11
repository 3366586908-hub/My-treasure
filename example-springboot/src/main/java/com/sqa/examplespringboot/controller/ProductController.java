package com.sqa.examplespringboot.controller;

import com.sqa.examplespringboot.common.Result;
import com.sqa.examplespringboot.entity.Product;
import com.sqa.examplespringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //http://localhost:8003/product/getList
    @GetMapping("/getList")
    @ResponseBody
    public Result<List<Product>> getList(String keyWords){
        List<Product> list = productService.getList(keyWords);
        return Result.success(list);
    }


}

