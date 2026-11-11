package com.sqa.examplespringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqa.examplespringboot.entity.Banner;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mapper是于mysql数据库进行交互的最终接口
 * 写sql语句的
 * 但是通常情况下会保持为空，因为mybatis的BaseMapper已经我们实现了相关的sql语句
 */
@Mapper
public interface BannerMapper extends BaseMapper<Banner> {

}
