package com.sqa.examplespringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("product")
public class Product {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String name;

    private String imgUrl;

    private String intro;

    private BigDecimal price;

    private Long createUser;

    private Date createDate;
}
