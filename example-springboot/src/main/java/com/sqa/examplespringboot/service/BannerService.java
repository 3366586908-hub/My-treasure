package com.sqa.examplespringboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sqa.examplespringboot.entity.Banner;

import java.util.List;

/**
 * 这是业务逻辑层的代码
 * 业务逻辑指的是与你相当需要实现的工作业务的相关代码
 */
public interface BannerService extends IService<Banner> {
    //声明一个查询Banner列表的方法
    List<Banner> getBannerList();

    IPage<Banner> getPage(Integer pageNum, Integer pageSize, String keyWords);

    Integer add(Banner banner);
}
