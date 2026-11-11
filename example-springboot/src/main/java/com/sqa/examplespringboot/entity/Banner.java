package com.sqa.examplespringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 对应mysql数据库中banner表
 *
 */
@Data
@TableName("banner")
public class Banner {
    //对应banner表中的id，Bigint转换到java中是Long
    //雪花算法+主键
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String name;
    private String imgUrl;
    private Integer sort;
}
