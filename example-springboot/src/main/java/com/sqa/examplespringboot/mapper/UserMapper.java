package com.sqa.examplespringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqa.examplespringboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper  extends BaseMapper<User> {
    Integer register(User user);
}
