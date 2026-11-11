package com.sqa.examplespringboot.controller;


import cn.hutool.db.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sqa.examplespringboot.common.Result;
import com.sqa.examplespringboot.entity.Banner;
import com.sqa.examplespringboot.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * controller是html中jquery的$.ajax操作的入口类
 * RequestMapping括号内所写的就是我们路径
 * 所以调用到当前类的总路径就是http://localhost:8001/banner
 */
@Controller
@RequestMapping("/banner")
public class BannerController {
    /**
     * 我们调用哪个业务逻辑就要定义那个业务逻辑的接口
     * 下方是需要调用BannerService这个业务逻辑层
     * 所以定义的方式是以下这种形式
     * 普通的接口的调用方式如下：
     * BannerService bannerService = new BannerServiceImpl();
     * 但是我们基于Springboot的依赖注入特性
     * 可以通过下面加Autowired注解形式完成上面普通接口的调用方式
     * 这种定义方式是将new的方式交由springboot进行管理，无需我们自己关心
     * 定义好以后我们可以直接在当前这个类下的任意方法中进行bannerService内定义的方法调用
     * ！！！注意：如果bannerService下面有红线，说明你的BannerServiceImpl类上方没有@Service注解
     * 这种定义方式就是靠@Service注解去找bannerService接口的实现类是哪一个
     *
     */
    @Autowired
    private BannerService bannerService;

    /**
     * 首页轮播图动态获取数据库内容调用的方法，或者叫接口(api)
     * GetMapping指的是Get请求，我们统筹会有两种请求方式，一个是Get一个是Post
     * GetMapping括号内定义的也是路径，所以访问到该api或叫方法的完整路径如下:
     * http://localhost:8001/banner/getList
     * @return
     */
    @GetMapping("/getList")
    @ResponseBody
    public Result<List<Banner>> getlist(){
        /**
         * bannerService就是我们上面@Autowired注解声明的接口，位置在service包下有一个叫BannerService的接口类
         * BannerService中声明有一个方法叫做List<Banner> getBanner
         * 该方法声明的实现一定可以在BannerServiceImpl中找到同名的
         * 该方法的返回类型是List<Banner>,所以我用List<Banner> list去接收这个返回值
         * 用Result统一返回格式把list返回给前端html
         */
        List<Banner> list = bannerService.getBannerList();
        return Result.success(list);
    }

    @GetMapping("/page")
    @ResponseBody
    public Result<IPage<Banner>> page(Integer pageNum, Integer pageSize,String keyWords){
        IPage<Banner> page = bannerService.getPage(pageNum,pageSize,keyWords);
        return Result.success(page);
    }

    @PostMapping("/add")
    @ResponseBody
    public Result<Banner> add(Banner banner){
        Integer result = bannerService.add(banner);
        if(result==1){
            return Result.success();
        }
        return Result.failed();
    }

    @GetMapping("/view")
    @ResponseBody
    public Result<Banner> view(String id){
        Banner banner = bannerService.getById(id);
        return Result.success(banner);
    }

    @PostMapping("/edit")
    @ResponseBody
    public Result<Banner> edit(Banner banner){
        boolean flag = bannerService.updateById(banner);
        if(flag){
            return Result.success();
        }
        return Result.failed();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Result<Banner> delete(String id){
        boolean flag = bannerService.removeById(id);
        if(flag){
            return Result.success();
        }
        return Result.failed();
    }
}
