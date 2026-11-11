package com.sqa.examplespringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqa.examplespringboot.common.Result;
import com.sqa.examplespringboot.entity.Banner;
import com.sqa.examplespringboot.mapper.BannerMapper;
import com.sqa.examplespringboot.service.BannerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Service非常关键，每一个Impl类都需要加
 * ServiceImpl就是MyBatis提供的操作Mapper的方法实现类，提供了通用的操作数据库的业务逻辑层方法
 * implements BannerService，跟我们的interface BannerService相关，当前类实现的就是BannerService层的方法声明
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    /**
     * 当前方法是获取数据库中所有的Banner表中的数据
     * this.list()是ServiceImpl中提供的方法，重点：方法中的首字母都是小写的
     * this.list()会调用我们写的mapper中的Mybatis提供的数据库查询语句去操作
     * @return
     */
    @Override
    public List<Banner> getBannerList() {
        QueryWrapper<Banner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        return this.list(queryWrapper);
    }

    @Override
    public IPage<Banner> getPage(Integer pageNum, Integer pageSize, String keyWords) {
        Page<Banner> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Banner> queryWrapper = new QueryWrapper<>();
        if(keyWords!=null && !keyWords.isEmpty()){
            queryWrapper.like("name",keyWords);
        }
        //不用手动做分页
        return this.page(page,queryWrapper);
    }

    @Override
    public Integer add(Banner banner) {
        //this.save就是mybatis给我们提供的插入数据到数据库的方法
        boolean flag = this.save(banner);
        if(flag){
            return 1;
        }
        return 0;
    }
}
